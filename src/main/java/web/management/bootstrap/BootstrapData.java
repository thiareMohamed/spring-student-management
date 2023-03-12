package web.management.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.management.entity.Student;
import web.management.repository.StudentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final StudentRepository studentRepository;
    @Override
    public void run(String... args) throws Exception {
        if (studentRepository.count() > 0) {
            return;
        }

        List<Student> students = List.of(
                Student.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john@doe.gmail")
                        .build(),
                Student.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("jane@doe.gmail")
                        .build(),
                Student.builder()
                        .firstName("Jack")
                        .lastName("Doe")
                        .email("jack@doe.gmail")
                        .build()
        );

        studentRepository.saveAll(students);

        System.out.println("Bootstrap data loaded. Students registered: " + studentRepository.count() + ".");
    }
}
