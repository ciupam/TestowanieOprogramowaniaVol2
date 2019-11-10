package pl.pw.edu.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FindCourseRequest {
    @NotNull
    private String start;
    @NotNull
    private String destination;
}
