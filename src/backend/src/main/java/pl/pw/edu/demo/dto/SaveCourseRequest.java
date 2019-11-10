package pl.pw.edu.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class SaveCourseRequest {
    @NotNull
    private String start;

    @NotNull
    private String destination;

    @NotNull
    private Double price;

    @NotNull
    private Double time;

}
