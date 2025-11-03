package com.lms.dev.controller;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dev.entity.Course;
import com.lms.dev.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        if (courseOpt.isPresent()) {
            return ResponseEntity.ok(courseOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID id, @RequestBody Course updatedCourse) {
        Optional<Course> courseOpt = courseService.updateCourse(id, updatedCourse);
        if (courseOpt.isPresent()) {
            return ResponseEntity.ok(courseOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
    }
}