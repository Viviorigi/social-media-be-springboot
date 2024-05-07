package com.duong.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duong.config.JwtProvider;
import com.duong.exceptions.UserException;
import com.duong.models.User;
import com.duong.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setGender(user.getGender());
		newUser.setCreatedAt(LocalDateTime.now());
		newUser.setReqUser(false);
		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isPresent()) {
			return user1.get();
		}
		throw new UserException("user not exist with userid" + userId);

	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {

		User reqUser = findUserById(reqUserId);

		User user2 = findUserById(userId2);
		
		if(user2.getFollower().contains(reqUserId)) {
			user2.getFollower().remove(reqUserId);
			reqUser.getFollowings().remove(userId2);
			user2.setFollowed(false);
		}else {
			user2.getFollower().add(reqUser.getId());
			reqUser.getFollowings().add(user2.getId());
			user2.setFollowed(true);
		}
		userRepository.save(reqUser);
		userRepository.save(user2);

		return user2;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty()) {
			throw new Exception("user not exist with userid " + userId);
		}

		User oldUser = user1.get();

		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender() !=null) {
			oldUser.setGender(user.getGender());
		}
		if(user.getBio() !=null) {
			oldUser.setBio(user.getBio());
		}
		if(user.getAvatar() !=null) {
			oldUser.setAvatar(user.getAvatar());
		}
		if(user.getBackground() !=null) {
			oldUser.setBackground(user.getBackground());
		}
		if(user.getLocation() !=null) {
			oldUser.setLocation(user.getLocation());
		}
		
		
		User updatedUser = userRepository.save(oldUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users=userRepository.searchUser(query);
		return users;
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email =JwtProvider.getEmailFromJwtToken(jwt);
		User user= userRepository.findByEmail(email);
		user.setReqUser(true);
		userRepository.save(user);
		return userRepository.findByEmail(email);
	}

}
