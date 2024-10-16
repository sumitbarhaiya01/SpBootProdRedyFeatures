package com.nt.SpBootRestClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer userId;
    private Integer id;
    private String tittle;
    private  boolean completed;
}
