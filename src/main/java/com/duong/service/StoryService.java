package com.duong.service;

import java.util.List;

import com.duong.models.Story;
import com.duong.models.User;

public interface StoryService {
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
	
}
