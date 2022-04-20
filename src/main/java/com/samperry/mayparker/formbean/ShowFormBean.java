package com.samperry.mayparker.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Date;


@Getter
@Setter
@ToString
public class ShowFormBean {

    private Integer id;

    //    @NotBlank(message = "Date is required")
    private Date date;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Time is required")
    private String time;
}
