package pl.pw.edu.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CitiesResponse {
    @NotNull
    private List<String> cities = new ArrayList<>();
}
