package com.thoughtworks.capability.gtb.restfulapidesign.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Integer id;
    private String groupName;
    List<Student> students;
    private String note;
}
