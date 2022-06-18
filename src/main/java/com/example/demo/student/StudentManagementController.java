package com.example.demo.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final Logger logger = LoggerFactory.getLogger(StudentManagementController.class);

    private final StudentService studentService;

    @Autowired
    public StudentManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
    hasRole('ROLE_')
    hasAnyRole('ROLE_')
    hasAuthority('')
    hasAnyAuthority('')
    */

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        logger.debug("StudentManagement:getStudent");
        return studentService.getStudent(studentId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ADMIN_TRAINEE')")
    public List<Student> getStudents() {
        logger.debug("StudentManagement:getStudents");
        return studentService.getStudents();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student addStudent(@RequestBody Student student) {
        logger.debug("StudentManagement:addStudent");
        return studentService.addStudent(student);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        logger.debug("StudentManagement:updateStudent");
        return studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        logger.debug("StudentManagement:deleteStudent");
        studentService.deleteStudent(studentId);
    }
}
