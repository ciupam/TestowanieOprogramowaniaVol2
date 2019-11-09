package pl.pw.edu.demo.controller;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pw.edu.demo.dto.SaveCourse;

import javax.validation.Valid;

@Log
@CrossOrigin
@RestController
@RequestMapping("/api")
public class MainControler {

    @PostMapping("/saveCourse")
    public  ResponseEntity<?> saveUser(@Valid @RequestBody SaveCourse request){
        log.info("New post request start = " + request.getStart());
        log.info("New post request destination = " + request.getDestination());
        log.info("New post request price = " + request.getPrice());
        log.info("New post request time = " + request.getTime());;
        return new ResponseEntity<>(request,HttpStatus.CREATED);
    }

    @GetMapping("/findCourse/{start}/{end}")
    public ResponseEntity<Boolean> getUser(@PathVariable String start, @PathVariable String end) {
        log.info("New get request start \"" + start + "\" end \"" + end + "\"");
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}