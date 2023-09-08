package com.shopbook.service;


import com.shopbook.entity.Book;
import com.shopbook.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList)
            if (book.isActive()) activeBookList.add(book);
        return activeBookList;
    }

    public Book findByID(Long id) { return bookRepository.findById(id).orElse(null);}

    public List<Book> findByCategory(String category) {
        List<Book> bookList = bookRepository.findByCategory(category);

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList)
            if (book.isActive())
                activeBookList.add(book);
        return activeBookList;
    }

    public List<Book> blurrySearch(String title) {
        List<Book> bookList = bookRepository.findByTitleContaining(title);
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList)
            if (book.isActive()) activeBookList.add(book);
        return activeBookList;
    }
}
