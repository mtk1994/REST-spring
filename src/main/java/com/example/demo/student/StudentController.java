package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//default path: http://localhost:8080/api/v1/student
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Get list of all students.
     * example usage: http://localhost:8080/api/v1/student
     *
     * @return List of all students.
     */
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    /**
     * Get student by its email address.
     * example usage: http://localhost:8080/api/v1/student/getByEmail?email=maria.m@gmail.com
     *
     * @param email Student's email address.
     * @return Student with given email address.
     */
    @GetMapping(value = "/getByEmail")
    public Student getStudentByEmail(@RequestParam String email) {
        Student student = studentService.getStudentsByEmail(email);
        if (student == null)
            throw new IllegalStateException("didn't find student with given email: " + email);
        return student;
    }

    /**
     * Get student by its email address. In this method we can see example of usage ResponseEntity to return the value.
     * example usage: http://localhost:8080/api/v1/student/getByEmailV2?email=maria.m@gmail.com
     *
     * @param email Student's email address.
     * @return Student with given email address
     */
    @GetMapping(value = "/getByEmailV2")
    public ResponseEntity<?> getStudentByEmailV2(@RequestParam String email) {
        Student student = studentService.getStudentsByEmail(email);
        if (student != null)
            return new ResponseEntity<>(student, HttpStatus.OK);
        return new ResponseEntity<>("Didn't find student with given email: " + email, HttpStatus.OK);
    }

    /**
     * Create a new student with given data.
     * example usage: http://localhost:8080/api/v1/student
     * example body:
     * {
     * "name" : "John",
     * "email" : "johnsmith@gmail.com",
     * "dateOfBirth" : "1995-12-17"
     * }
     *
     * @param student student to create. Take this object directly from the HTTP POST request body.
     */
    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    /**
     * Delete student by its ID.
     * example usage: http://localhost:8080/api/v1/student/1
     *
     * @param studentId The ID of the student to be deleted.
     */
    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    /**
     * Update student's data.
     * example usage: http://localhost:8080/api/v1/student/1?name=JOHNY?email=johny@gmail.com
     *
     * @param studentId Student ID to identify him.
     * @param name      New student name.
     * @param email     New student email address.
     */
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
