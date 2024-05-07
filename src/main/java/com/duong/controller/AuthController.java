package com.duong.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duong.config.JwtProvider;
import com.duong.models.User;
import com.duong.repository.UserRepository;
import com.duong.request.LoginRequest;
import com.duong.response.AuthResponse;
import com.duong.service.CustomerUserDetailsService;
import com.duong.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	// /auth/signup
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user ) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("this email already used with another account");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setGender(user.getGender());
		newUser.setCreatedAt(LocalDateTime.now());
		newUser.setReqUser(false);
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"Register Success");
		
		return res;
		
		
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token, "login success");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetails= customerUserDetailsService.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("password not match");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
	
	@PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization",required = false) String jwt) {
	   
		User user=userService.findUserByJwt(jwt);
		user.setReqUser(false);
		userRepository.save(user);
        return ResponseEntity.ok("Logout successful");
    }
	
}
