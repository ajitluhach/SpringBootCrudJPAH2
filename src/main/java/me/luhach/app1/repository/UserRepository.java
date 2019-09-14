package me.luhach.app1.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import me.luhach.app1.entity.User;

public interface UserRepository extends Repository<User, Long> {
	public User save(User user);
	public void deleteById(Long id);
	public User findById(Long id);
	public boolean existsById(Long id);
	public List<User> findAll();
}
