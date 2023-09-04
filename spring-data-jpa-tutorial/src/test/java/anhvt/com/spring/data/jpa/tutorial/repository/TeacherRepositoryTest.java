package anhvt.com.spring.data.jpa.tutorial.repository;

import anhvt.com.spring.data.jpa.tutorial.entity.Course;
import anhvt.com.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseJava = Course.builder()
                .courseName("Java")
                .credit(2)
                .build();

        Course coursePhp = Course.builder()
                .courseName("PHP")
                .credit(2)
                .build();

        List<Course> courseList = Arrays.asList(courseJava, coursePhp);
        Teacher teacher = Teacher.builder()
                .firstName("Teacher")
                .lastName("1")
//                .courses(courseList)
                .build();

        teacherRepository.save(teacher);
    }
}