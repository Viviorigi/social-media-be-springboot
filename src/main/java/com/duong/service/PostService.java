package com.duong.service;

import java.util.List;

import com.duong.models.Post;

public interface PostService {
	
	public Post createNewPost(Post post,Integer userId) throws Exception ;
	
	public String deletePost(Integer postId,Integer userId) throws Exception  ;
	
	public List<Post> findPostByUserId(Integer userId) ;
	
	public Post findPostById(Integer postId) throws Exception;
	
	public List<Post> findAllPost();
	
	public Post savedPost(Integer postId,Integer userId) throws Exception;
	
	public Post likePost(Integer postId,Integer userId) throws Exception;
}
