package com.duong.service;

import java.util.List;

import com.duong.models.Reels;
import com.duong.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;
	
}
