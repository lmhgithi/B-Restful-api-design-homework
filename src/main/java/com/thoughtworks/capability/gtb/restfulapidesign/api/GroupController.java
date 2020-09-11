package com.thoughtworks.capability.gtb.restfulapidesign.api;


import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.SimpleException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroups() {
        return ResponseEntity.ok(groupService.getGroups());
    }

    @PostMapping("/groups")
    public ResponseEntity<List<Group>> group() {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.group());
    }

    @PatchMapping("/groups/{id}/{name}")
    public ResponseEntity<List<Group>> changeGroupName(@PathVariable Integer id,
                                         @PathVariable String name) throws SimpleException {
        List<Group> groups = groupService.changeGroupName(id, name);
        return ResponseEntity.status(HttpStatus.OK).body(groups);
    }
}
