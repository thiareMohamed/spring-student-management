package web.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.management.entity.Student;
import web.management.service.StudentService;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());

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