package com.Soheily.LerningSpringboot.resource;

import com.Soheily.LerningSpringboot.Service.UserService;
import com.Soheily.LerningSpringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/Users")
public class UserResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> fetchUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET,
    path = "{userUid}")
    public ResponseEntity<?> fetchUser(@PathVariable("userUid")UUID userUid){
        Optional<User> user = userService.getUser(userUid);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user "+userUid+ " was not found.") );

    }



}
 class ErrorMessage {
    private   String errorMessage;

     public ErrorMessage(String errorMessage) {
         this.errorMessage = errorMessage;
     }

     public String getErrorMessage() {
         return errorMessage;
     }

     public void setErrorMessage(String errorMessage) {
         this.errorMessage = errorMessage;
     }
 }