package com.duong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.duong.models.Reels;
import com.duong.models.User;
import com.duong.service.ReelsService;
import com.duong.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt) {
		User reqUser= userService.findUserByJwt(jwt);
		
		Reels createReels= reelsService.createReel(reel, reqUser);
		
		return createReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		return reelsService.findAllReels();
		
	}
	
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable("userId") Integer userId) throws Exception {
		
		List<Reels> reels= reelsService.findUserReels(userId);
		
		return reels;
		
	}
	
}
