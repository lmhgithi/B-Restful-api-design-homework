package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    static Integer id = 11;

    static private List<Student> students = new ArrayList<Student>() {
        {
            add(new Student(1, Gender.MALE, "沈乐棋", null));
            add(new Student(2, Gender.FEMALE, "徐慧慧", null));
            add(new Student(3, Gender.MALE, "陈思聪", null));
            add(new Student(4, Gender.MALE, "王江林", null));
            add(new Student(5, Gender.FEMALE, "王登宇", null));
            add(new Student(6, Gender.MALE, "杨思雨", null));
            add(new Student(7, Gender.MALE, "江雨舟", null));
            add(new Student(8, Gender.MALE, "廖燊", null));
            add(new Student(9, Gender.FEMALE, "胡晓", null));
            add(new Student(10, Gender.FEMALE, "但杰", null));
            add(new Student(11, Gender.OTHER, "小白", null));
        }
    };

    public Integer getGeneratedId(){
        id+=1;
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student addStudent(Student student) {
        student.setId(getGeneratedId());
        students.add(student);
        return student;
    }

    public Student findStudentsByIndex(Integer index) {
        return students.get(index);
    }

    public Student findStudentsByName(String name) {
        Optional<Student> student = students.stream().filter(s -> s.getName().equals(name)).findFirst();
        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public void deleteByName(String name) {
        Student student = findStudentsByName(name);
        students.remove(student);
    }

    public List<Student> getShuffleStudents() {
        List<Student> students = depCopy(getStudents());
        Collections.shuffle(students);
        return students;
    }

    public List<Student> findStudentsByGender(Gender gender) {
        return students.stream().filter(student ->
            student.getGender().equals(gender)
        ).collect(Collectors.toList());
    }

    public void update(Integer id, Student student) {
        student.setId(id+1);
        students.set(id, student);
    }

    public static List<Student> depCopy(List<Student> students) {
        return new ArrayList<>(students);
    }

}
