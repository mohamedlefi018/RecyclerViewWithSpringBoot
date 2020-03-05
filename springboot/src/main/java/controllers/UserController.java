package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.JsonArray;

import entities.User;
import services.UserServices;
import services.UserServicesImp;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
	UserServicesImp userServices;

	@GetMapping
	public List<User> getUsers() {
		return userServices.getUsers();
	}
	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable int id) {
		return userServices.getUserById(id);
	}
	@PostMapping
	public User addUser(@RequestBody User user) {
		userServices.addUser(user);
		return user;
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		userServices.updateUser(user);
		return user;
	}

	@DeleteMapping("/deleteUserById/{id}")
	public User deleteUser(@PathVariable int id) {
		userServices.removeUser(id);
		return new User();
	}

}
