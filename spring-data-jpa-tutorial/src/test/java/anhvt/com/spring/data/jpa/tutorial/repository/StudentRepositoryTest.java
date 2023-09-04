package anhvt.com.spring.data.jpa.tutorial.repository;

import anhvt.com.spring.data.jpa.tutorial.entity.Guardian;
import anhvt.com.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("vutrunganh1@gmail.com")
                .firstName("vu")
                .lastName("trung anh")
//                .guardianEmail("mother")
//                .guardianEmail("mother@gmail.com")
//                .guardianMobile("09999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("mother")
                .email("mother@gmail.com")
                .mobile("9999999")
                .build();

        Student student = Student.builder()
                .emailId("vutrunganh2@gmail.com")
                .firstName("vu")
                .lastName("trung anh")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList= "+ studentList);
    }

    @Test
    public void findByFirstName() {
        List<Student> studentList =
                studentRepository.findByFirstName("vu");
        System.out.println("studentList= "+ studentList);

    }

    @Test
    public void findByFirstNameContaining() {
        List<Student> studentList =
                studentRepository.findByFirstNameContaining("v");
        System.out.println("studentList= "+ studentList);

    }

    @Test
    public void findByLastNameNotNull() {
        List<Student> studentList =
                studentRepository.findByLastNameNotNull();
        System.out.println("studentList= "+ studentList);

    }

    @Test
    public void findByGuardianName() {
        List<Student> studentList =
                studentRepository.findByGuardianName("mother");
        System.out.println("studentList= "+ studentList);

    }

    @Test
    public void findByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmail("vutrunganh1@gmail.com");
        System.out.println("student = "+ student);

    }

    @Test
    public void findFirstNameByEmailAddress() {
        String student =
                studentRepository.getFirstNameStudentByEmail("vutrunganh1@gmail.com");
        System.out.println("student = "+ student);

    }

    @Test
    public void findStudentByEmailAddressNative() {
        Student student =
                studentRepository.getStudentByEmailNative("vutrunganh1@gmail.com");
        System.out.println("student = "+ student);

    }

    @Test
    public void findStudentByEmailAddressNativeNameParam() {
        Student student =
                studentRepository.getStudentByEmailNativeNameParam("vutrunganh1@gmail.com");
        System.out.println("student = "+ student);

    }

    @Test
    public void updateFirstNameByEmail() {
        studentRepository.updateStudentNameByEmail("Vu Trung", "vutrunganh1@gmail.com");


    }
}