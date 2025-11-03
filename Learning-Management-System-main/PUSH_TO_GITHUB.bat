@echo off
echo ========================================
echo Learning Management System - Git Setup
echo ========================================
echo.

cd "Learning-Management-System-main"

echo Step 1: Checking Git installation...
git --version
if %errorlevel% neq 0 (
    echo ERROR: Git is not installed or not in PATH
    pause
    exit /b 1
)
echo.

echo Step 2: Initializing Git repository...
if exist .git (
    echo Git repository already exists
) else (
    git init
    echo Git repository initialized!
)
echo.

echo Step 3: Configuring Git user (if not already set)...
git config user.name >nul 2>&1
if %errorlevel% neq 0 (
    echo Git user.name not configured. Please run:
    echo   git config --global user.name "Your Name"
    echo   git config --global user.email "your.email@example.com"
    echo.
)
echo.

echo Step 4: Staging all files...
git add .
echo Files staged successfully!
echo.

echo Step 5: Creating initial commit...
git commit -m "Initial commit: Learning Management System with Spring Boot and React"
echo.

echo Step 6: Repository status...
git log --oneline -1
echo.

echo ========================================
echo NEXT STEPS TO PUSH TO GITHUB:
echo ========================================
echo.
echo 1. Create a new repository on GitHub
echo    - Go to https://github.com/new
echo    - Name your repository
echo    - DO NOT initialize with README, .gitignore, or license
echo    - Click "Create repository"
echo.
echo 2. Copy the repository URL (HTTPS format)
echo.
echo 3. Run these commands (replace with your repository URL):
echo.
echo    git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
echo    git branch -M main
echo    git push -u origin main
echo.
echo ========================================
echo.
pause

