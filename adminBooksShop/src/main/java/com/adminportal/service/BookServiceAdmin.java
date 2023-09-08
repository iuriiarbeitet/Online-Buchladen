package com.adminportal.service;

import com.adminportal.entity.Book;
import com.adminportal.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceAdmin {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}
	
	public Book findOne(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public void removeOne(Long id) {
		bookRepository.deleteById(id);
	}
}
