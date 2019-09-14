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

import me.luhach.app1.entity.Book;
import me.luhach.app1.repository.BookRepository;

@RestController(value = "/book")
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		Book Book = bookRepository.findById(id); 
		return new ResponseEntity<>(Book, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> Books = bookRepository.findAll(); 
		return new ResponseEntity<>(Books, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Book> createBook(@RequestBody Book Book, UriComponentsBuilder ucb) {
		
		Book = bookRepository.save(Book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/{id}").buildAndExpand(Book.getBookId()).toUri());
		
		return new ResponseEntity<Book>(Book, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book Book) {
		if (!bookRepository.existsById(Book.getBookId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		Book = bookRepository.save(Book);
		return new ResponseEntity<>(Book, HttpStatus.OK);
		
	}
}
