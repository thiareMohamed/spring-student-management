package web.management.service;

import web.management.entity.Student;

import java.util.Optional;

public interface StudentService {
    Optional<Student> findById(Long id);
    Iterable<Student> findAll();
    Student save(Student student);
    Student update(Student student, Long id);
    boolean deleteById(Long id);
}
