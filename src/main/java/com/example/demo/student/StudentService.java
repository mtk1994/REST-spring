package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Get list of all students.
     *
     * @return List of all students.
     */
    public List<Student> getStudents (){
        return studentRepository.findAll();
    }

    /**
     * Get student by its email address.
     *
     * @param email Student's email address.
     * @return Student with given email address.
     */
    public Student getStudentsByEmail (String email){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail (email);
        return studentByEmail.orElse(null);
    }

    /**
     * Create a new student with given data.
     *
     * @param student student to create. Take this object directly from the HTTP POST request body.
     */
    public void addNewStudent (Student student) {
        // check if email is already in use
        checkIfEmailTaken (student.getEmail());
        studentRepository.save(student);
    }

    /**
     * Delete student by its ID.
     *
     * @param studentId The ID of the student to be deleted.
     */
    public void deleteStudent (Long studentId) {
        boolean exists = studentRepository.existsById (studentId);
        if (!exists)
            throw new IllegalStateException("Student with id: " + studentId + " does not exists");

        studentRepository.deleteById (studentId);
    }

    /**
     * Update student's data.
     *
     * @param studentId Student ID to identify him.
     * @param name New student name.
     * @param email New student email address.
     */
    @Transactional
    public void updateStudent (Long studentId, String name, String email){
        Optional<Student> studentOptional = studentRepository.findById (studentId);
        if (studentOptional.isEmpty())
            throw new IllegalStateException("Student with id: " + studentId + " does not exists");

        Student student = studentOptional.get();

        //check name
        if (name != null && name.length() > 0 && !Objects.equals (student.getName(), name))
            student.setName (name);

        //check email
        if (email != null && email.length() > 0 && !Objects.equals (student.getEmail(), email)) {
            // check if email is already in use
            checkIfEmailTaken (student.getEmail());
            student.setEmail (email);
        }
    }

    // Utils
    private boolean checkIfEmailTaken (String email){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
        if (studentByEmail.isPresent())
            throw new IllegalStateException("Email address already taken.");
        return false;
    }
}
