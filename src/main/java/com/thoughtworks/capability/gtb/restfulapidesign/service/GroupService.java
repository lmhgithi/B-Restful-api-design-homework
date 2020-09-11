package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.SimpleException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository){
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.getGroups();
    }

    public List<Group> group() {
        initGroup();
        List<Student> students = studentRepository.getShuffleStudents();
        int perGroup = students.size() / 6;
        int restToAdd = 0;
        int studentsSize = studentRepository.getStudents().size();
        if (perGroup * 6 < studentsSize) {
            restToAdd = studentsSize - perGroup * 6;
        }

        int total = 0;
        for (int i = 0; i < 6; i += 1) {
            List<Student> tmp = new ArrayList<>();
            int tmpAdd = restToAdd > 0 ? perGroup + 1 : perGroup;
            for (int j = 0; j < tmpAdd; j += 1) {
                if (total <= studentsSize) {
                    tmp.add(studentRepository.findStudentsByIndex(total));
                    total += 1;
                }
            }
            restToAdd -= 1;
            groupRepository.getGroups().get(i).setStudents(tmp);
        }

        return groupRepository.getGroups();
    }

    private void initGroup() {
        List<Group> groups = new ArrayList<Group>() {
            {
                add(new Group(1, groupRepository.getGroupsName().get(0), new ArrayList<>(), null));
                add(new Group(2, groupRepository.getGroupsName().get(1), new ArrayList<>(), null));
                add(new Group(3, groupRepository.getGroupsName().get(2), new ArrayList<>(), null));
                add(new Group(4, groupRepository.getGroupsName().get(3), new ArrayList<>(), null));
                add(new Group(5, groupRepository.getGroupsName().get(4), new ArrayList<>(), null));
                add(new Group(6, groupRepository.getGroupsName().get(5), new ArrayList<>(), null));
            }
        };
        groupRepository.setGroup(groups);
    }


    public List<Group> changeGroupName(Integer id, String name) throws SimpleException {
        if (groupRepository.updateGroupNameById(id, name)) {
            return groupRepository.getGroups();
        } else {
            throw new SimpleException();
        }
    }
}
