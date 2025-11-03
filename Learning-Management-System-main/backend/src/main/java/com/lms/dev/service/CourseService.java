package com.lms.dev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.dev.entity.Course;
import com.lms.dev.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(UUID id, Course updatedCourse) {
        if (id == null || updatedCourse == null) {
            return Optional.empty();
        }
        Optional<Course> existingCourseOpt = courseRepository.findById(id);
        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();
            existingCourse.setCourse_name(updatedCourse.getCourse_name());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setP_link(updatedCourse.getP_link());
            existingCourse.setPrice(updatedCourse.getPrice());
            existingCourse.setInstructor(updatedCourse.getInstructor());
            existingCourse.setY_link(updatedCourse.getY_link());
            return Optional.of(courseRepository.save(existingCourse));
        }
        return Optional.empty();
    }

    public void deleteCourse(UUID id) {
        if (id != null) {
            courseRepository.deleteById(id);
        }
    }
}
