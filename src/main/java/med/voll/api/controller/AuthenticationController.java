package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.model.dto.DataTokenJwtDto;
import med.voll.api.model.dto.AuthenticationData;
import med.voll.api.model.entities.User;
import med.voll.api.model.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Batista, C.
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid AuthenticationData authenticationData) throws  Exception {

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
            var authentication = authenticationManager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generetedToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new DataTokenJwtDto(tokenJWT));

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


    }


}
