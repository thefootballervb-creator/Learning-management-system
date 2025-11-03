# GitHub Setup Guide

## Prerequisites
1. Install Git from: https://git-scm.com/download/win
2. Create a GitHub account: https://github.com
3. Create a new repository on GitHub (don't initialize with README)

## Steps to Push to GitHub

### 1. Initialize Git Repository
```bash
cd Learning-Management-System-main
git init
```

### 2. Configure Git (if not already done)
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Add All Files
```bash
git add .
```

### 4. Create Initial Commit
```bash
git commit -m "Initial commit: Learning Management System with Spring Boot and React"
```

### 5. Add Remote Repository
Replace `YOUR_USERNAME` and `YOUR_REPO_NAME` with your GitHub details:
```bash
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
```

### 6. Push to GitHub
```bash
git branch -M main
git push -u origin main
```

## If You Get Authentication Errors

### Option 1: Use Personal Access Token
1. Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Generate new token with `repo` scope
3. Use token as password when prompted

### Option 2: Use SSH (Recommended)
```bash
# Generate SSH key
ssh-keygen -t ed25519 -C "your.email@example.com"

# Copy public key
cat ~/.ssh/id_ed25519.pub

# Add to GitHub: Settings → SSH and GPG keys → New SSH key
# Then use SSH URL:
git remote set-url origin git@github.com:YOUR_USERNAME/YOUR_REPO_NAME.git
```

## Project Structure
```
Learning-Management-System-main/
├── backend/          # Spring Boot application
│   ├── src/
│   ├── pom.xml
│   └── ...
├── frontend/         # React application
│   ├── src/
│   ├── package.json
│   └── ...
├── .gitignore
└── README.md
```

## Features Included
- ✅ 26+ courses with domain-specific content
- ✅ 20-25 quiz questions per course
- ✅ User authentication (JWT)
- ✅ Admin and Student dashboards
- ✅ Course enrollment and progress tracking
- ✅ Assessment system with certificates
- ✅ Discussion forums
- ✅ Sky blue theme
- ✅ Java 21 LTS compatibility
- ✅ Spring Boot 3.5.7
- ✅ MySQL database

