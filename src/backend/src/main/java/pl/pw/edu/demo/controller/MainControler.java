package pl.pw.edu.demo.controller;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pw.edu.demo.algorithm.Algorithm;
import pl.pw.edu.demo.dto.CitiesResponse;
import pl.pw.edu.demo.dto.CourseResponse;
import pl.pw.edu.demo.dto.FindCourseRequest;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import javax.validation.Valid;
import java.util.List;

@Log
@CrossOrigin
@RestController
@RequestMapping("/api")
public class MainControler {
    Algorithm algorithm = Algorithm.algorithmSingleton();

    @PostMapping("/saveCourse")
    public ResponseEntity<?> saveUser(@Valid @RequestBody SaveCourseRequest request) {
        log.info("New post request start = " + request.getStart());
        log.info("New post request destination = " + request.getDestination());
        log.info("New post request price = " + request.getPrice());
        log.info("New post request time = " + request.getTime());
        int status = algorithm.addCours(request);
        if (status == 200) {
            return new ResponseEntity<>(request, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(request,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/findCourse")
    public ResponseEntity<CourseResponse> getCourse(@Valid @RequestBody FindCourseRequest request) {
        log.info("New get request start \"" + request.getStart() + "\" destination \"" + request.getDestination() + "\"");
        CourseResponse result = algorithm.findBestConnection(request.getStart(), request.getDestination(),true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<CitiesResponse> getCities(){
        List<String> cities = algorithm.getCities();
        CitiesResponse response = new CitiesResponse();
        response.setCities(cities);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}