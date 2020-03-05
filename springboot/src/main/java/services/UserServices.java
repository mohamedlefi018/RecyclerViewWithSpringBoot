package services;

import java.util.List;

import org.springframework.stereotype.Service;

import entities.User;
public interface UserServices {
	
	String addUser(User u);
	User getUserById(int id);
	List<User>getUsers();
	void removeUser(int id);
	void updateUser(User user);

}
