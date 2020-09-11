package com.thoughtworks.capability.gtb.restfulapidesign.api;


import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.SimpleException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }

    @GetMapping("/students/genders/{gender}")
    public ResponseEntity<List<Student>> getStudentsByGender(@PathVariable Gender gender) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByGender(gender));
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<Student> getStudentsByName(@PathVariable String name) throws SimpleException {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByName(name));
    }

    @DeleteMapping("/students/{name}")
    public ResponseEntity deleteStudents(@PathVariable String name) throws SimpleException {
        studentService.deleteStudents(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws SimpleException {
        Student studentAdded = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentAdded);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) throws SimpleException {
        Student studentUpdated = studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(studentUpdated);
    }

}
