package com.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.management.Service.AuthorService;
import com.management.dto.AuthorRequest;
import com.management.dto.AuthorResponse;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // CREATE
    @PostMapping
    public AuthorResponse addAuthor(@RequestBody AuthorRequest dto) {
        return authorService.addAuthor(dto);
    }

    // READ ALL
    @GetMapping
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest dto) {
        return authorService.updateAuthor(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "Author deleted successfully.";
    }
}