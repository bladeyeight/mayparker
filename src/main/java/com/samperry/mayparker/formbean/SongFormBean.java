package com.samperry.mayparker.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class SongFormBean {

    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;


}