package com.quandev.springbootrestcurdpostgress.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class TutorialReq {


    private String title;


    private String description;


    private Boolean published;

    private String status;

    private Integer page;

    private Integer size;
}
