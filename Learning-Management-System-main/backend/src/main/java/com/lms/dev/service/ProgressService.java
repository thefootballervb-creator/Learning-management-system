package com.lms.dev.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dev.dto.ProgressRequest;
import com.lms.dev.entity.Course;
import com.lms.dev.entity.Progress;
import com.lms.dev.entity.User;
import com.lms.dev.repository.CourseRepository;
import com.lms.dev.repository.ProgressRepository;
import com.lms.dev.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    public ResponseEntity<String> updateProgress(ProgressRequest request) {
        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        UUID userId = request.getUserId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        UUID courseId = request.getCourseId();
        if (courseId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        float playedTime = request.getPlayedTime();
        float duration = request.getDuration();

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();
            Progress progress = progressRepository.findByUserAndCourse(user, course);
            if (progress != null && (progress.getPlayedTime() == 0 || progress.getPlayedTime()<= playedTime)) {
                progress.setPlayedTime(playedTime);
                progress.setDuration(duration);
                progressRepository.save(progress);
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid playedTime");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
    }

	public float getProgress(UUID userId, UUID courseId) {
		if (userId == null) {
            return 0;
        }
        if (courseId == null) {
            return 0;
        }
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();
            Progress progress = progressRepository.findByUserAndCourse(user, course);
            if (progress != null) {
                return progress.getPlayedTime();
            }
        }
		return 0; 
	}

	public ResponseEntity<String> updateDuration(ProgressRequest request) {
        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        UUID userId = request.getUserId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        UUID courseId = request.getCourseId();
        if (courseId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        float newDuration = request.getDuration();

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();
            Progress progress = progressRepository.findByUserAndCourse(user, course);

            if (progress != null) {
                progress.setDuration(newDuration);
                progressRepository.save(progress);

                return ResponseEntity.ok("Duration updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found for the given user and course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
        }
    }

    
}

