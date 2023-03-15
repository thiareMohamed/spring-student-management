package web.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.management.entity.Student;
import web.management.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsRestController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return (List<Student>) studentService.findAll();
    }
}
