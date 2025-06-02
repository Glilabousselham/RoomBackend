package org.glila.room.v1.module.auth;

import org.glila.room.v1.module.auth.dto.AuthResponse;
import org.glila.room.v1.module.auth.dto.LoginRequest;
import org.glila.room.v1.module.auth.dto.RegisterRequest;
import org.glila.room.v1.module.user.UserRepository;
import org.glila.room.v1.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;

    public AuthResponse register(RegisterRequest request) throws Exception {

        // check if email exists
        if(this.userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new Exception("Email is already exist");
        }
        // check if username exists
        if(this.userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new Exception("Username is already exist");
        }



        User user = new User();

        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        this.userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) throws Exception {
        // check if email exists
        User user = this.userRepository.findByUsername(request.getUsername()).orElseThrow(()->new Exception("Username not found"));

        if(!user.getPassword().equals(request.getPassword())){
            new Exception("Password wrong");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);

    }
}
