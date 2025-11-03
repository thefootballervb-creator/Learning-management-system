package com.lms.dev.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.dev.dto.FeedbackRequest;
import com.lms.dev.entity.Course;
import com.lms.dev.entity.Feedback;
import com.lms.dev.repository.CourseRepository;
import com.lms.dev.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CourseRepository courseRepository;

    public List<Feedback> getFeedbacksForCourse(UUID courseId) {
        if (courseId == null) {
            return Collections.emptyList();
        }
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            return courseOpt.get().getFeedbacks();
        }
        return Collections.emptyList();
    }

    public String submitFeedback(FeedbackRequest fr) {
        if (fr == null) {
            return "feedback submission failed";
        }
        UUID courseId = fr.getCourse_id();
        if (courseId == null) {
            return "feedback submission failed";
        }
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Feedback feedback = new Feedback();
            feedback.setCourse(courseOpt.get());
            feedback.setComment(fr.getComment());
            feedbackRepository.save(feedback);
            return "feedback submitted successfully";
        }
        return "feedback submission failed";
    }
}

