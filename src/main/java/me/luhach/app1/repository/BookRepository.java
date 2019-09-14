package me.luhach.app1.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import me.luhach.app1.entity.Book;

public interface BookRepository extends Repository<Book, Long> {
	public Book save(Book user);
	public void deleteById(Long id);
	public Book findById(Long id);
	public boolean existsById(Long id);
	public List<Book> findAll();
}
