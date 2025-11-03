package com.lms.dev.service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.dev.dto.DiscussionRequest;
import com.lms.dev.entity.Course;
import com.lms.dev.entity.Discussion;
import com.lms.dev.repository.DiscussionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final CourseService courseService;

    public List<Discussion> getDiscussionsCourse(UUID courseId) {
        if (courseId == null) {
            return Collections.emptyList();
        }
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            return discussionRepository.findByCourse(courseOpt.get());
        }
        return Collections.emptyList();
    }
    
    public Discussion createDiscussion(DiscussionRequest discussionRequest) {
        if (discussionRequest == null || discussionRequest.getCourse_id() == null) {
            throw new IllegalArgumentException("Invalid discussion request");
        }
        Optional<Course> courseOpt = courseService.getCourseById(discussionRequest.getCourse_id());
        if (!courseOpt.isPresent()) {
            throw new IllegalArgumentException("Course not found");
        }
        Discussion discussion = new Discussion();
        discussion.setUserName(discussionRequest.getName());
        discussion.setCourse(courseOpt.get());
        discussion.setContent(discussionRequest.getContent());
        return discussionRepository.save(discussion);
    }
}
