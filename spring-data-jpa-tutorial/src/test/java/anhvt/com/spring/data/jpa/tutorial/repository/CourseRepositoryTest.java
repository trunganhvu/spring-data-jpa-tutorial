package anhvt.com.spring.data.jpa.tutorial.repository;

import anhvt.com.spring.data.jpa.tutorial.entity.Course;
import anhvt.com.spring.data.jpa.tutorial.entity.Student;
import anhvt.com.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printALlCourse() {
        List<Course> courseList =
                courseRepository.findAll();
        System.out.println("courseList: " + courseList);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("teacher")
                .lastName("python")
                .build();

        Course course = Course.builder()
                .courseName("Python")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllCoursePaging() {
        Pageable firstPage = PageRequest.of(0, 3);

        Pageable secondPage = PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPage).getContent();

        long totalElements =
                courseRepository.findAll(firstPage).getTotalElements();

        long totalPage =
                courseRepository.findAll(firstPage).getTotalPages();

        System.out.println("course: " + courses);
        System.out.println("totalElements: " + totalElements);
        System.out.println("totalPage: " + totalPage);
    }

    @Test
    public void findAllCourseSorting() {
        Pageable firstPage = PageRequest.of(
                0,
                3,
                Sort.by("courseName").descending()
                .and(Sort.by("credit"))
        );

        List<Course> courses =
                courseRepository.findAll(firstPage).getContent();

        long totalElements =
                courseRepository.findAll(firstPage).getTotalElements();

        long totalPage =
                courseRepository.findAll(firstPage).getTotalPages();

        System.out.println("course: " + courses);
        System.out.println("totalElements: " + totalElements);
        System.out.println("totalPage: " + totalPage);
    }

    @Test
    public void printFindByCourseNameContaining() {
        Pageable firstPage = PageRequest.of(
                0,
                3);

        List<Course> courses = courseRepository.findByCourseNameContaining("p", firstPage)
                .getContent();
        System.out.println("courses: " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("teacher")
                .lastName("C++")
                .build();

        Student student3 = Student.builder()
                .emailId("vutrunganh3@gmail.com")
                .firstName("vu")
                .lastName("trung anh")
                .build();

        Student student4 = Student.builder()
                .emailId("vutrunganh4@gmail.com")
                .firstName("tran")
                .lastName("trung anh")
                .build();
        List<Student> students = new ArrayList<>();
        students.add(student3);
        students.add(student4);

        Course course = Course.builder()
                .courseName("C++")
                .credit(15)
                .teacher(teacher)
                .students(students)
                .build();

        courseRepository.save(course);
    }
}