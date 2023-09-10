package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Long type in JpaRepository - Student's class ID type
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // it is equivalent to sql query: SELECT * FROM student WHERE email = ?;
    // TODO it looks like the whole logic is automatically generated
    Optional<Student> findStudentByEmail (String email);

    // it is equivalent to sql query: SELECT * FROM student WHERE id = ?;
    Optional<Student> findStudentById (Long id);

}
