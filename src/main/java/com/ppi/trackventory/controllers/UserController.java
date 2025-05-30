package com.ppi.trackventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.Profile;
import com.ppi.trackventory.models.Rol;
import com.ppi.trackventory.models.User;
import com.ppi.trackventory.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception {

        Rol rol = new Rol();
        rol.setId(1l);
        rol.setName("NORMAL");

        Profile profile = new Profile();
        profile.setRol_prf(rol);

        user.setProfile(profile);

        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok().body("Token is valid.");
    }
}
