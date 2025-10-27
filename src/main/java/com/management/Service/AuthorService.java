package com.management.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.AuthorRequest;
import com.management.dto.AuthorResponse;
import com.management.model.Author;
import com.management.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    public AuthorResponse addAuthor(AuthorRequest dto) { Author author = modelMapper.map(dto, Author.class);
        Author saved = authorRepository.save(author);
        return modelMapper.map(saved, AuthorResponse.class);
    }

    // READ ALL
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return modelMapper.map(author, AuthorResponse.class);
    }

    // UPDATE
    public AuthorResponse updateAuthor(Long id, AuthorRequest dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(dto.getName());
        Author updated = authorRepository.save(author);
        return modelMapper.map(updated, AuthorResponse.class);
    }

    // DELETE
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
