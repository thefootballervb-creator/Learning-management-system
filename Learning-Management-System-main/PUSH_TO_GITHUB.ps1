# Learning Management System - GitHub Push Script
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Learning Management System - Git Setup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Set-Location "Learning-Management-System-main"

Write-Host "Step 1: Checking Git installation..." -ForegroundColor Yellow
try {
    $gitVersion = git --version
    Write-Host "Git found: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "ERROR: Git is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Git from https://git-scm.com/download/win" -ForegroundColor Yellow
    pause
    exit 1
}
Write-Host ""

Write-Host "Step 2: Initializing Git repository..." -ForegroundColor Yellow
if (Test-Path .git) {
    Write-Host "Git repository already exists" -ForegroundColor Yellow
} else {
    git init
    Write-Host "Git repository initialized!" -ForegroundColor Green
}
Write-Host ""

Write-Host "Step 3: Checking Git user configuration..." -ForegroundColor Yellow
$userName = git config user.name 2>$null
$userEmail = git config user.email 2>$null
if (-not $userName -or -not $userEmail) {
    Write-Host "Git user not configured. Please run:" -ForegroundColor Yellow
    Write-Host "  git config --global user.name `"Your Name`"" -ForegroundColor White
    Write-Host "  git config --global user.email `"your.email@example.com`"" -ForegroundColor White
    Write-Host ""
} else {
    Write-Host "User: $userName <$userEmail>" -ForegroundColor Green
}
Write-Host ""

Write-Host "Step 4: Staging all files..." -ForegroundColor Yellow
git add .
Write-Host "Files staged successfully!" -ForegroundColor Green
Write-Host ""

Write-Host "Step 5: Creating initial commit..." -ForegroundColor Yellow
git commit -m "Initial commit: Learning Management System

Features:
- Spring Boot 3.5.7 backend with Java 21
- React frontend with sky blue theme
- 26+ courses with domain-specific content
- 20-25 quiz questions per course
- JWT authentication
- Admin and Student dashboards
- Course enrollment and progress tracking
- Assessment system with certificates
- Discussion forums
- MySQL database integration"
Write-Host ""

Write-Host "Step 6: Repository status..." -ForegroundColor Yellow
git log --oneline -1
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "NEXT STEPS TO PUSH TO GITHUB:" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "1. Create a new repository on GitHub" -ForegroundColor Yellow
Write-Host "   - Go to https://github.com/new" -ForegroundColor White
Write-Host "   - Name your repository" -ForegroundColor White
Write-Host "   - DO NOT initialize with README, .gitignore, or license" -ForegroundColor White
Write-Host "   - Click 'Create repository'" -ForegroundColor White
Write-Host ""
Write-Host "2. Copy the repository URL (HTTPS format)" -ForegroundColor Yellow
Write-Host ""
Write-Host "3. Run these commands (replace with your repository URL):" -ForegroundColor Yellow
Write-Host ""
Write-Host "   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git" -ForegroundColor Gray
Write-Host "   git branch -M main" -ForegroundColor Gray
Write-Host "   git push -u origin main" -ForegroundColor Gray
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

