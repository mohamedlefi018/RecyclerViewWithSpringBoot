package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserRepository;
import entities.User;
@Service

public class UserServicesImp implements UserServices{
	
	@Autowired
	UserRepository userRepository;
	
	
	public String addUser(User user)
	{
		userRepository.save(user);
		return "user added";
	}
	
	public List<User>getUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.getOne(id);
	}
	

	public void removeUser(int id) {
		userRepository.deleteById(id);
	}
	public void updateUser(User user) {
		userRepository.save(user);
	}

}
