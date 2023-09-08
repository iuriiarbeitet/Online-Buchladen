package com.shopbook.service;

import com.shopbook.entity.Book;
import com.shopbook.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Mock
    private BookRepository mockBookRepository;

    @Test
    public void testFindAll() {
        List<Book> allBooks = new ArrayList<>();
        allBooks.add(new Book());
        allBooks.add(new Book());

        Mockito.when(mockBookRepository.findAll()).thenReturn(allBooks);

        BookService bookService = new BookService(mockBookRepository);

        List<Book> activeBooks = bookService.findAll();

        assertEquals(2, activeBooks.size());
    }

    @Test
    public void testFindByID() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);

        Mockito.when(mockBookRepository.findById(id)).thenReturn(Optional.of(book));

        BookService bookService = new BookService(mockBookRepository);

        Book foundBook = bookService.findByID(id);

        assertEquals(id, foundBook.getId());
    }

    @Test
    public void testFindByCategory() {
        String category = "Category 1";
        List<Book> categoryBooks = new ArrayList<>();
        categoryBooks.add(new Book());
        categoryBooks.add(new Book());

        Mockito.when(mockBookRepository.findByCategory(category)).thenReturn(categoryBooks);

        BookService bookService = new BookService(mockBookRepository);

        List<Book> activeBooks = bookService.findByCategory(category);

        assertEquals(2, activeBooks.size());
    }

    @Test
    public void testBlurrySearch() {
        String title = "Book";
        List<Book> searchResults = new ArrayList<>();
        searchResults.add(new Book());
        searchResults.add(new Book());

        Mockito.when(mockBookRepository.findByTitleContaining(title)).thenReturn(searchResults);

        BookService bookService = new BookService(mockBookRepository);

        List<Book> activeBooks = bookService.blurrySearch(title);

        assertEquals(2, activeBooks.size());
    }

    @Test
    public void testFindByIDNotFound() {
        Long id = 1L;

        Mockito.when(mockBookRepository.findById(id)).thenReturn(Optional.empty());

        BookService bookService = new BookService(mockBookRepository);

        Book foundBook = bookService.findByID(id);

        assertNull(foundBook);
    }

}