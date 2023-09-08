package com.adminportal.controller;


import com.adminportal.entity.Book;
import com.adminportal.service.BookServiceAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

	private final BookServiceAdmin bookServiceAdmin;

	public BookController(BookServiceAdmin bookServiceAdmin) {
		this.bookServiceAdmin = bookServiceAdmin;
	}

	@GetMapping(value = "/add")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

	@PostMapping(value = "/add")
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookServiceAdmin.save(book);

		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
                    Files.newOutputStream(new File("src/main/resources/static/image/book/" + name).toPath()));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:bookList";
	}
	
	@GetMapping("/bookInfo")
	public String bookInfo(@RequestParam("id") Long id, Model model) {
		Book book = bookServiceAdmin.findOne(id);
		model.addAttribute("book", book);
		
		return "bookInfo";
	}
	
	@GetMapping("/updateBook")
	public String updateBook(@RequestParam("id") Long id, Model model) {
		Book book = bookServiceAdmin.findOne(id);
		model.addAttribute("book", book);
		
		return "updateBook";
	}
	
	@PostMapping(value="/updateBook")
	public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookServiceAdmin.save(book);
		
		MultipartFile bookImage = book.getBookImage();
		
		if(!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/book/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
                        Files.newOutputStream(new File("src/main/resources/static/image/book/" + name).toPath()));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/book/bookInfo?id="+book.getId();
	}
	
	@GetMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookServiceAdmin.findAll();
		model.addAttribute("bookList", bookList);		
		return "bookList";
		
	}
	
	@PostMapping(value="/remove")
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		bookServiceAdmin.removeOne(Long.parseLong(id.substring(8)));
		List<Book> bookList = bookServiceAdmin.findAll();
		model.addAttribute("bookList", bookList);
		
		return "redirect:/book/bookList";
	}

}
