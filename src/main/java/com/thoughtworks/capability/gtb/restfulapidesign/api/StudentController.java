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
@RequestMapping("/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) Gender gender) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents(gender));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Student> getStudentsByName(@PathVariable String name) throws SimpleException {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByName(name));
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudents(@PathVariable String name) throws SimpleException {
        studentService.deleteStudents(name);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws SimpleException {
        Student studentAdded = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentAdded);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id,
                                                 @RequestBody Student student) throws SimpleException {
        Student studentUpdated = studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(studentUpdated);
    }

}
