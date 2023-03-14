package web.management.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.management.entity.Student;
import web.management.repository.StudentRepository;
import com.github.javafaker.Faker;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final StudentRepository studentRepository;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        if (studentRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 50; i++) {
            Student student = new Student();

            student.setFirstName(faker.name().firstName());
            student.setLastName(faker.name().lastName());
            student.setEmail(faker.internet().emailAddress());

            studentRepository.save(student);
        }

        System.out.println("Bootstrap data loaded. Students registered: " + studentRepository.count() + ".");
    }
}
