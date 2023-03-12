package web.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
