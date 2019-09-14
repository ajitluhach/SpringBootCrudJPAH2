package me.luhach.app1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import me.luhach.app1.entity.User;
import me.luhach.app1.repository.UserRepository;

@RestController(value = "/user")
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userRepository.findById(id); 
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepository.findAll(); 
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
		
		user = userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		
		return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		if (!userRepository.existsById(user.getUserId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		user = userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
}
