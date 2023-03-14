package web.management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.management.entity.Student;
import web.management.service.StudentService;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return this.findPaginated(
                1,
                5,
                "id",
                String.valueOf(Sort.Direction.ASC),
                model);
    }

    @GetMapping("/students")
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());

        return "students";
    }

    @GetMapping("/students/{page}/{size}")
    public String findPaginated(
            @PathVariable int page,
            @PathVariable int size,
            @RequestParam(value = "field", defaultValue = "id") String field,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            Model model
    ) {
        if (page < 1) page = 1;
        if (size < 1) size = 5;

        Page<Student> students = studentService.findAll(page, size, field, direction);
        Iterable<Student> studentList = students.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", students.getTotalPages());
        model.addAttribute("totalItems", students.getTotalElements());
        model.addAttribute("students", studentList);
        model.addAttribute("field", field);
        model.addAttribute("direction", direction);
        model.addAttribute("reverseDirection", direction.equalsIgnoreCase("ASC") ? "DESC" : "ASC");

        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);

        return "create_student";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid student Id:" + id)
        );

        model.addAttribute("student", student);

        return "edit_student";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudentForm(@PathVariable("id") Long id, Model model) {
        studentService.deleteById(id);

        return "redirect:/students";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);

        return "redirect:/students";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(
            @PathVariable("id") Long id, @ModelAttribute("student") Student student
    ) {
        studentService.update(student, id);

        return "redirect:/students";
    }
}