package pl.pw.edu.demo.controller;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pw.edu.demo.algorithm.Algorithm;
import pl.pw.edu.demo.dto.CourseResponse;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import javax.validation.Valid;

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

    @GetMapping("/findCourse/{start}/{end}")
    public ResponseEntity<CourseResponse> getUser(@PathVariable String start, @PathVariable String end) {
        log.info("New get request start \"" + start + "\" end \"" + end + "\"");
        CourseResponse result = algorithm.findBestConnection(start, end);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}