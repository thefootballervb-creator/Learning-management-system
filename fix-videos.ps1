# Fix Video Links Script
# Run this once the backend is fully started

Write-Host "Fixing Video Links for All Courses" -ForegroundColor Cyan
Write-Host "====================================" -ForegroundColor Cyan
Write-Host ""

# Try update-video-links endpoint first
$updateUrl = "http://localhost:8080/api/admin/update-video-links"
Write-Host "Trying update-video-links endpoint..." -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri $updateUrl -Method POST -TimeoutSec 10
    
    if ($response.success) {
        Write-Host ""
        Write-Host "SUCCESS!" -ForegroundColor Green
        Write-Host "Updated $($response.updatedCount) courses with correct video links!" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "NOW REFRESH YOUR BROWSER (F5)!" -ForegroundColor Yellow
        Write-Host "The AWS course will now show AWS video, not Python!" -ForegroundColor Green
        exit 0
    }
} catch {
    Write-Host "Update endpoint not ready (may need backend restart)" -ForegroundColor Yellow
    Write-Host ""
}

# Try reinit-courses as alternative
$reinitUrl = "http://localhost:8080/api/admin/reinit-courses"
Write-Host "Trying reinit-courses endpoint..." -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri $reinitUrl -Method POST -TimeoutSec 15
    
    if ($response.success) {
        Write-Host ""
        Write-Host "SUCCESS!" -ForegroundColor Green
        Write-Host "Reinitialized all courses with correct video links!" -ForegroundColor Cyan
        Write-Host "Total courses: $($response.count)" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "NOW REFRESH YOUR BROWSER (F5)!" -ForegroundColor Yellow
        Write-Host "All courses now have correct videos!" -ForegroundColor Green
        exit 0
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host ""
    Write-Host "The backend may still be starting." -ForegroundColor Yellow
    Write-Host "Check the backend PowerShell window for:" -ForegroundColor Cyan
    Write-Host "  'Started LearningManagementSystemApplication'" -ForegroundColor White
    Write-Host ""
    Write-Host "Once you see that message, run this script again." -ForegroundColor Yellow
    exit 1
}

