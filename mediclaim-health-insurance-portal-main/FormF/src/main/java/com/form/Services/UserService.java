package com.form.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.form.dto.UserDto;
import com.form.entity.User;

public interface UserService 
{

	User findByUsername(String username);

	List<UserDto> addUser(UserDto userDto);

	List<User> getAllUsers();

	User save(User user);

	String uploadImage1(MultipartFile mpf, Long id) throws IOException, InterruptedException;

	User test(String userName);

	// delete by username

	void deleteUserByID(long id);

	// Get user id

	User getUserID(long id);

	public User getById(long id);
}
