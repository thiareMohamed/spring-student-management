package web.management.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web.management.entity.Student;
import web.management.repository.StudentRepository;
import web.management.service.StudentService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, Long id) {
        Student fetchedStudent = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found")
        );

        fetchedStudent.setFirstName(student.getFirstName());
        fetchedStudent.setLastName(student.getLastName());
        fetchedStudent.setEmail(student.getEmail());

        return studentRepository.save(fetchedStudent);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            studentRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
