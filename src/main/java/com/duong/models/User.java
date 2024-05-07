package com.duong.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")


public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private String avatar;
	private String bio;
	private Boolean reqUser;
	private String background;
	private String location;
	private LocalDateTime createdAt;
	
	private Boolean followed;
	private List<Integer> follower=new ArrayList<>();
	
	private List<Integer> followings=new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost=new ArrayList<>();
	
	
}
