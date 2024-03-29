package com.example.demo.student;

import com.example.demo.lang.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new CustomException(String.format("student with id %d does not exists", studentId),
                                HttpStatus.NOT_FOUND));
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new CustomException("email taken", HttpStatus.BAD_REQUEST);
        }
        return studentRepository.save(student);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (exists) {
            studentRepository.deleteById(studentId);
            logger.warn("Student {} was deleted!", studentId);
        } else {
            throw new CustomException(String.format("student with id %d does not exists", studentId),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public Student updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(
                        String.format("student with id %d does not exists", studentId), HttpStatus.NOT_FOUND));
        if (StringUtils.hasText(name) && !Objects.equals(student.getName(), name)) {
            student.setName(name);
            logger.info("set new name {}", name);
        }
        if (StringUtils.hasText(email) && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new CustomException("email taken", HttpStatus.BAD_REQUEST);
            }
            student.setEmail(email);
        }
        return studentRepository.save(student);
    }
}
