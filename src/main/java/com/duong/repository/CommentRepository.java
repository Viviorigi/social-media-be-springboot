package com.duong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duong.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {
	
}
