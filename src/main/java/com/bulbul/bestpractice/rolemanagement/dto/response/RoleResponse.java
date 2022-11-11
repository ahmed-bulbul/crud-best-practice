package com.bulbul.bestpractice.rolemanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponse {
    private Long id;
    private String name;
    private Long groupId;
    private String groupName;
    //private String name;
}