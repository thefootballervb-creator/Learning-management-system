# Quick script to update video links
$url = "http://localhost:8080/api/admin/update-video-links"

Write-Host "Updating video links for all courses..." -ForegroundColor Yellow
Write-Host ""

try {
    $response = Invoke-RestMethod -Uri $url -Method POST
    
    if ($response.success) {
        Write-Host "SUCCESS!" -ForegroundColor Green
        Write-Host "Updated $($response.updatedCount) courses with correct video links" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "NOW REFRESH YOUR BROWSER!" -ForegroundColor Yellow
        Write-Host "The AWS course will now show the AWS video!" -ForegroundColor Green
    } else {
        Write-Host "Response: $($response.message)" -ForegroundColor Yellow
    }
} catch {
    Write-Host "ERROR: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host ""
    if ($_.Exception.Response.StatusCode -eq 401) {
        Write-Host "The backend needs to be restarted!" -ForegroundColor Yellow
        Write-Host "1. Stop the backend server (close the PowerShell window)" -ForegroundColor White
        Write-Host "2. Restart it using the same command" -ForegroundColor White
        Write-Host "3. Wait 30 seconds for it to start" -ForegroundColor White
        Write-Host "4. Run this script again" -ForegroundColor White
    } elseif ($_.Exception.Message -like "*connection*") {
        Write-Host "Backend is not running or still starting." -ForegroundColor Yellow
        Write-Host "Wait 30 seconds and try again." -ForegroundColor Yellow
    }
}

