package com.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.management.Service.BooksService;
import com.management.dto.BooksRequest;
import com.management.dto.BooksResponse;




@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService bookService;

    // CREATE
    @PostMapping
    public BooksResponse addBook(@RequestBody BooksRequest dto) {
        return bookService.addBook(dto);
    }

    // READ ALL
    @GetMapping
    public List<BooksResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public BooksResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BooksResponse updateBook(@PathVariable Long id, @RequestBody BooksRequest dto) {
        return bookService.updateBook(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully.";
    }

    // BORROW BOOK (Transactional)
    @PostMapping("/borrow/{memberId}/{bookId}")
    public String borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        bookService.borrowBook(memberId, bookId);
        return "Book borrowed successfully.";
    }
}