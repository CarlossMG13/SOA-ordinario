package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.apartamentos.services.UserService;
import com.blue.apartamentos.models.UserModel;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    //Obtener todos los usuarios
    @GetMapping
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById
    (@PathVariable Long id) {
        Optional<UserModel> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                .build());
    }

    // Crear un nuevo usuario
    @PostMapping
    public UserModel createUser(@RequestBody UserModel user){
        return userService.saveUser(user);
    }

    //Actualizar usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser
    (@PathVariable Long id, @RequestBody UserModel userDetails) {
        Optional<UserModel> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setNombre(userDetails.getNombre());
            user.setApellido(userDetails.getApellido());
            user.setEmail(userDetails.getEmail());
            user.setTelefono(userDetails.getTelefono());
            user.setFechaNacimiento(userDetails.getFechaNacimiento());
            user.setDocumentoIdentidad(userDetails.getDocumentoIdentidad());
            user.setDireccion(userDetails.getDireccion());
            user.setTipoUsuario(userDetails.getTipoUsuario());
            user.setEstadoUsuario(userDetails.getEstadoUsuario());
            UserModel updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<UserModel> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
