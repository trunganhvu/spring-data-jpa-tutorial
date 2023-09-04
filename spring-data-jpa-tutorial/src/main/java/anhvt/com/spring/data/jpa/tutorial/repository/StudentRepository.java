package anhvt.com.spring.data.jpa.tutorial.repository;

import anhvt.com.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String firstName);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);

    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmail(String email);

    //Java persistence query language
    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getFirstNameStudentByEmail(String email);

    // Native query
    @Query(
            value= "select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailNative(String email);

    // Native query
    @Query(
            value= "select * from tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    Student getStudentByEmailNativeNameParam(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name=?1 where email_address=?2",
            nativeQuery = true
    )
    int updateStudentNameByEmail(String firstName, String email);
}
