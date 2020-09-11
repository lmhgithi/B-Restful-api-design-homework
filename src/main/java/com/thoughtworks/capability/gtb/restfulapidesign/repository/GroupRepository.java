package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    static private List<Group> groups = new ArrayList<>();

    static private List<String> groupsName = new ArrayList<String>() {
        {
            add("Team 1");
            add("Team 2");
            add("Team 3");
            add("Team 4");
            add("Team 5");
            add("Team 6");
        }
    };

    public List<Group> getGroups() {
        return groups;
    }

    public List<String> getGroupsName() {
        return groupsName;
    }

    public void setGroup(List<Group> groups) {
        GroupRepository.groups = groups;
    }

    public boolean updateGroupNameById(Integer id, String groupName) {
        if (groups.size() <= id || groups.stream().anyMatch(group -> group.getGroupName().equals(groupName))) {
            return false;
        }
        Group group = groups.get(id - 1);
        group.setGroupName(groupName);
        groupsName.set(id-1, groupName);
        return true;
    }
}
