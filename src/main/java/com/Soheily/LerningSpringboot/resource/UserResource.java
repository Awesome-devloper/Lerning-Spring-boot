package com.Soheily.LerningSpringboot.resource;

import com.Soheily.LerningSpringboot.Service.UserService;
import com.Soheily.LerningSpringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
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
    public List<User> fetchUsers(@QueryParam("gender") String gender
            //,
                               //  @QueryParam("ageLessThan") String ageLessThan
    )
    {
        //System.out.println(gender);
        //System.out.println(ageLessThan);

        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @RequestMapping(method = RequestMethod.GET,
    path = "{userUid}")
    public ResponseEntity<?> fetchUser(@PathVariable("userUid")UUID userUid){
        Optional<User> user = userService.getUser(userUid);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user "+userUid+ " was not found.") );

    }
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> insertNewUser(@RequestBody User user){
        int result=userService.InsertUser(user);
        if (result==1)
            return  ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> updateUser(@RequestBody User user){
        int result=userService.updateUser(user);
        if (result==1)
            return  ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userUid}"
    )
    public ResponseEntity<User> deleteUser(@PathVariable("userUid") UUID uuid){
        int result=userService.removeUser(uuid);
        if (result==1)
            return  ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
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