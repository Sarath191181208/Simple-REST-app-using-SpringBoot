package com.sarath.init.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("The register student already exist with the same Email ID");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean isStudentExist = studentRepository.existsById(id);
        if (!isStudentExist) {
            throw new IllegalStateException("Student with Id" + id + "doesn't exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with the ID" + id + "doesn't exist"));

        var isNameNull = (name == null) || (name.length() < 1);
        var isNameSame = Objects.equals(student.getName(), name);

        if (!isNameNull && !isNameSame) {
            student.setName(name);
        }

        var isEmailNull = (email == null) || (email.length() < 1);
        var isEmailSame = Objects.equals(student.getEmail(), email);

        if (!isEmailNull && !isEmailSame) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent())
                throw new IllegalStateException("Email Already Taken");
            student.setEmail(email);
        }

    }

}
