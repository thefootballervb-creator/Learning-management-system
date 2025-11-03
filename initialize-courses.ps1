# Course Initialization Script
# This script manually initializes courses in the database via API call

$backendUrl = "http://localhost:8080"
$loginUrl = "$backendUrl/api/auth/signin"
$initUrl = "$backendUrl/api/admin/init-courses"
$updateVideoUrl = "$backendUrl/api/admin/update-video-links"

Write-Host "Course Initialization Script" -ForegroundColor Cyan
Write-Host "=============================" -ForegroundColor Cyan

# Update video links (no auth required - public endpoint)
Write-Host "`nStep 1: Updating video links for existing courses..." -ForegroundColor Yellow
try {
    $updateResponse = Invoke-WebRequest -Uri $updateVideoUrl -Method POST -UseBasicParsing
    $updateData = $updateResponse.Content | ConvertFrom-Json
    
    if ($updateData.success) {
        Write-Host "✓ Video links updated successfully!" -ForegroundColor Green
        Write-Host "Updated $($updateData.updatedCount) courses" -ForegroundColor Cyan
    } else {
        Write-Host "Response: $($updateData.message)" -ForegroundColor Yellow
    }
} catch {
    Write-Host "✗ Error updating video links: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "The backend may still be starting. Wait a few seconds and try again." -ForegroundColor Yellow
}

# Login as admin first
Write-Host "`nStep 2: Logging in as admin..." -ForegroundColor Yellow
$loginBody = @{
    email = "admin@gmail.com"
    password = "admin123"
} | ConvertTo-Json

try {
    $loginResponse = Invoke-WebRequest -Uri $loginUrl -Method POST -Body $loginBody -ContentType "application/json" -UseBasicParsing
    $loginData = $loginResponse.Content | ConvertFrom-Json
    $token = $loginData.token
    
    if ($token) {
        Write-Host "✓ Login successful!" -ForegroundColor Green
        
        # Initialize courses
        Write-Host "`nStep 3: Initializing courses..." -ForegroundColor Yellow
        $headers = @{
            "Authorization" = "Bearer $token"
            "Content-Type" = "application/json"
        }
        
        $initResponse = Invoke-WebRequest -Uri $initUrl -Method POST -Headers $headers -UseBasicParsing
        $initData = $initResponse.Content | ConvertFrom-Json
        
        Write-Host "Response: $($initData.message)" -ForegroundColor $(if ($initData.success) { "Green" } else { "Yellow" })
        if ($initData.count) {
            Write-Host "Total courses: $($initData.count)" -ForegroundColor Green
        }
        
        Write-Host "`n✓ Course initialization complete!" -ForegroundColor Green
        Write-Host "`nYou can now refresh your browser to see the courses." -ForegroundColor Cyan
    } else {
        Write-Host "✗ Failed to get authentication token" -ForegroundColor Red
    }
} catch {
    Write-Host "✗ Error: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Make sure the backend is running on port 8080" -ForegroundColor Yellow
}

