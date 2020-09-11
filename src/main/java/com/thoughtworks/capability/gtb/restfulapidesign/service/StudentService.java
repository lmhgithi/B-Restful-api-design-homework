package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.SimpleException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public List<Student> getStudentsByGender(Gender gender) {
        return studentRepository.findStudentsByGender(gender);

    }

    public Student getStudentsByName(String name) throws SimpleException {
        Student student = studentRepository.findStudentsByName(name);
        if (student != null) {
            return student;
        } else {
            throw new SimpleException();
        }

    }

    public void deleteStudents(String name) throws SimpleException {
        Integer preSize = studentRepository.getStudents().size();
        studentRepository.deleteByName(name);
        Integer curSize = studentRepository.getStudents().size();
        if (preSize.equals(curSize)) {
            throw new SimpleException();
        }
    }

    public Student addStudent(Student student) throws SimpleException {
        if (studentRepository.findStudentsByName(student.getName()) != null ) {
            throw new SimpleException();
        }
        studentRepository.addStudent(student);
        return studentRepository.findStudentsByName(student.getName());
    }

    public Student updateStudent(Integer id, Student student) throws SimpleException {
        if (studentRepository.findStudentsByName(student.getName()) != null ) {
            throw new SimpleException();
        }
        studentRepository.update(id-1, student);
        return studentRepository.findStudentsByName(student.getName());
    }
}
