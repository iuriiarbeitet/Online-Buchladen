package com.shopbook.controller;

import com.shopbook.entity.Book;
import com.shopbook.entity.User;
import com.shopbook.service.BookService;
import com.shopbook.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for handling book-related operations.
 */
@Controller
public class BookController {
    private final UserService userService;
    private final BookService bookService;

    /**
     * Constructs a new BookController with the specified services.
     *
     * @param userService   The user service.
     * @param bookService   The book service.
     */
    public BookController(
                          UserService userService,
                          BookService bookService
                          ) {
        this.userService = userService;
        this.bookService = bookService;
    }

    /**
     * Displays the bookshelf page, showing a list of books.
     *
     * @param model      The model for the view.
     * @param principal  The authenticated user.
     * @return           The view name for the bookshelf page.
     */
    @RequestMapping("/bookshelf")
    public String bookshelf(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("activeAll", true);

        return "bookshelf";
    }

    /**
     * Displays the book detail page for a specific book.
     *
     * @param id         The ID of the book.
     * @param model      The model for the view.
     * @param principal  The authenticated user.
     * @return           The view name for the book detail page.
     */
    @RequestMapping("/bookDetail")
    public String bookDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Book book = bookService.findByID(id);

        model.addAttribute("book", book);

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "bookDetail";
    }

    }
