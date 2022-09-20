package com.api.controller;


import com.api.entity.User;
import com.api.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Api(tags = "Api-Controller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<User>> defaultApi(){
        log.info("DEFAULT-API invoked..");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getData(@RequestParam("userId") String id){
        log.info("GET-API invoked..");
        return new ResponseEntity<>(userService.getUser(StringUtils.isBlank(id)  ? 0 : Integer.parseInt(id)), HttpStatus.OK);
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Void> addData(@RequestBody List<User> data){
        log.info("POST-API invoked..");
        userService.saveOrUpdate(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable("id") String id){
        log.info("DELETE-API invoked..");
        userService.delete(StringUtils.isBlank(id)  ? 0 : Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
