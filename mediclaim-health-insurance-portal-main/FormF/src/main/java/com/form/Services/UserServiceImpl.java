package com.form.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.form.dto.UserDto;
import com.form.entity.User;
import com.form.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) 
	{
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) 
	{
		return userRepository.findByUsername(username);
	}

	private final List<UserDto> users = new ArrayList<>();

	public List<UserDto> getUsers()
	{
		return users;
	}

	public List<UserDto> addUser(UserDto user)
	{
		users.add(user);
		return users;
	}

	@Override
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}


	@Override
	public User save(User user) 
	{
		return userRepository.save(user);
	}

	@Override
	public String uploadImage1(MultipartFile mpf, Long id) throws IOException, InterruptedException
	{
		
		String realFilePath = "C:\\Users\\pragy\\OneDrive\\Desktop\\jio internship\\FormF\\src\\main\\resources\\static\\images\\" + id + ".jpeg"; //C:\Users\pragy\OneDrive\Desktop\jio internship\FormF\src\main\resources\images
		User user = userRepository.getById(id);
		String path1 = "images\\" + id + ".jpeg";
		user.setImagePath(path1);
		userRepository.save(user);
		File file = new File(realFilePath);// E:\test
		copyInputStreamToFile(mpf.getInputStream(), file);
		Thread.sleep(1000);
		return "home";
	}

	private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException 
	{
		// append = false
		try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
			int read;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		}
	}
	
	@Override
	public User test(String userName) {
		return userRepository.findByUsername(userName);
	}

	//delete user id
	
	@Override
	public void deleteUserByID(long id) {
		this.userRepository.deleteById(id);
		
	}

	//getUserID
	@Override
	public User getUserID(long id) {
		Optional <User> optional = userRepository.findById(id);
		
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		}else {
			throw new RuntimeException("Employee not found for id ::"+id);
		}
		return user;
	}
	
	@Override
	public User getById(long id) {
		User user = userRepository.getById(id);
		return user;
	}	
}