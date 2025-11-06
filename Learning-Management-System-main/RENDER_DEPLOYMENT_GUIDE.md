# Render Deployment Guide

## Problem: Database Connection Error

If you see `Communications link failure` or `Connection refused` errors, it means the application cannot connect to MySQL. This guide will help you set up the database on Render.

## Step-by-Step Setup

### 1. Create MySQL Database on Render

1. Go to your Render dashboard: https://dashboard.render.com
2. Click **"New +"** → **"PostgreSQL"** (or **"MySQL"** if available)
3. Configure the database:
   - **Name**: `lms-database` (or any name you prefer)
   - **Database**: `learning_management` (or leave default)
   - **User**: (auto-generated or choose your own)
   - **Region**: Choose closest to your web service
   - **Plan**: Free tier is fine for testing
4. Click **"Create Database"**
5. Wait for the database to be provisioned (2-3 minutes)

### 2. Get Database Connection Details

Once the database is created:

1. Click on your database service
2. Find the **"Connections"** section
3. You'll see:
   - **Internal Database URL**: `mysql://user:password@hostname:port/database`
   - **Hostname**: `xxxx.xxxx.render.com`
   - **Port**: `3306` (usually)
   - **Database Name**: `learning_management` (or what you set)
   - **Username**: `xxxx`
   - **Password**: `xxxx` (click "Show" to reveal)

### 3. Configure Web Service Environment Variables

1. Go to your **lms-backend** web service
2. Click **"Environment"** tab
3. Add these environment variables:

```
SPRING_DATASOURCE_URL = jdbc:mysql://<hostname>:<port>/<database>?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME = <username>
SPRING_DATASOURCE_PASSWORD = <password>
```

**Example:**
```
SPRING_DATASOURCE_URL = jdbc:mysql://dpg-xxxxx-a.oregon-postgres.render.com:3306/learning_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME = lms_user
SPRING_DATASOURCE_PASSWORD = abc123xyz
```

### 4. Important Notes

- **Use Internal Database URL**: Render provides an "Internal Database URL" that only works from within Render's network. Use this for your web service.
- **External Connection**: If you need to connect from outside Render, use the "External Connection" details, but for web service → database, use internal.
- **SSL**: Set `useSSL=false` for Render's internal connections (they're already secure).
- **Port**: Render automatically sets the `PORT` environment variable, and our `application.yml` uses it.

### 5. Redeploy

After adding the environment variables:

1. Go to your web service
2. Click **"Manual Deploy"** → **"Deploy latest commit"**
3. Wait for deployment (3-5 minutes)
4. Check logs to ensure database connection succeeds

### 6. Verify Connection

Once deployed, check the logs:

1. Go to your web service → **"Logs"** tab
2. Look for:
   - ✅ `HikariPool-1 - Starting...` followed by `HikariPool-1 - Start completed.`
   - ✅ `Started LearningManagementSystemApplication`
   - ❌ If you see `Communications link failure`, the database credentials are wrong

### 7. Initialize Courses

After successful deployment:

1. Your backend should be running at: `https://your-service.onrender.com`
2. Call the initialization endpoint:
   ```
   POST https://your-service.onrender.com/api/admin/reinit-courses
   ```
   You can use:
   - Postman
   - curl: `curl -X POST https://your-service.onrender.com/api/admin/reinit-courses`
   - Or add a button in your frontend

## Troubleshooting

### Error: "Connection refused"
- **Cause**: Database environment variables not set or incorrect
- **Fix**: Double-check all three environment variables in Render dashboard

### Error: "Access denied for user"
- **Cause**: Wrong username or password
- **Fix**: Copy credentials exactly from Render database dashboard

### Error: "Unknown database"
- **Cause**: Database name doesn't exist
- **Fix**: Check database name in Render, or let the app create it (if `createDatabaseIfNotExist=true`)

### Error: "No open ports detected"
- **Cause**: Application failed to start (usually due to database connection)
- **Fix**: Fix database connection first, then port will be detected automatically

## Quick Checklist

- [ ] MySQL database created on Render
- [ ] Database connection details copied
- [ ] Environment variables added to web service:
  - [ ] `SPRING_DATASOURCE_URL`
  - [ ] `SPRING_DATASOURCE_USERNAME`
  - [ ] `SPRING_DATASOURCE_PASSWORD`
- [ ] Web service redeployed
- [ ] Logs show successful database connection
- [ ] Courses initialized via API endpoint

## Alternative: Use Render's PostgreSQL

If MySQL is not available, you can switch to PostgreSQL:

1. Create PostgreSQL database instead
2. Update `pom.xml` to use PostgreSQL driver:
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
   </dependency>
   ```
3. Update `application.yml`:
   ```yaml
   spring:
     datasource:
       driver-class-name: org.postgresql.Driver
       url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/learning_management}
   ```
4. Update Hibernate dialect to PostgreSQL

But for now, MySQL should work fine on Render.

