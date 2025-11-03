package com.lms.dev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.dev.dto.QuestionRequest;
import com.lms.dev.entity.Course;
import com.lms.dev.entity.Questions;
import com.lms.dev.repository.CourseRepository;
import com.lms.dev.repository.QuestionRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CourseRepository courseRepository;

    public QuestionService(QuestionRepository questionRepository, CourseRepository courseRepository) {
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
    }

    public Questions addQuestion(QuestionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        UUID courseId = request.getCourseId();
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Questions question = new Questions();
        question.setQuestion(request.getQuestion());
        question.setOption1(request.getOption1());
        question.setOption2(request.getOption2());
        question.setOption3(request.getOption3());
        question.setOption4(request.getOption4());
        question.setAnswer(request.getAnswer());
        question.setCourse(course);

        return questionRepository.save(question);
    }

    public Questions updateQuestion(UUID id, QuestionRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("Question ID cannot be null");
        }
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        Questions question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        UUID courseId = request.getCourseId();
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        question.setQuestion(request.getQuestion());
        question.setOption1(request.getOption1());
        question.setOption2(request.getOption2());
        question.setOption3(request.getOption3());
        question.setOption4(request.getOption4());
        question.setAnswer(request.getAnswer());
        question.setCourse(course);

        return questionRepository.save(question);
    }

    public void deleteQuestion(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Question ID cannot be null");
        }
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepository.deleteById(id);
    }

    public List<Questions> getAllQuestionsByCourse(UUID courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return questionRepository.findByCourse(course);
    }

    public Optional<Questions> getQuestionById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return questionRepository.findById(id);
    }
}
