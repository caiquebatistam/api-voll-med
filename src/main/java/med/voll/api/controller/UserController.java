/*
package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.dto.UserRegistrationData;
import med.voll.api.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createUser")
@Transactional(rollbackFor = Exception.class)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserRegistrationData userRegistrationData) {
       userService.createUser(userRegistrationData.login(), userRegistrationData.password());

       return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
*/
