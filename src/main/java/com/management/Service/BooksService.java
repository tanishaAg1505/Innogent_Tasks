package com.management.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.BooksRequest;
import com.management.dto.BooksResponse;
import com.management.model.Author;
import com.management.model.Books;
import com.management.model.Member;
import com.management.repository.AuthorRepository;
import com.management.repository.BooksRepository;
import com.management.repository.MemberRepository;

@Service
public class BooksService {

    @Autowired
    private BooksRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

   @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    public BooksResponse addBook(BooksRequest dto) {
        // Find author first
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Map simple fields manually
        Books book = new Books();
        book.setTitle(dto.getTitle());
        book.setStock(dto.getStock());
        book.setAuthor(author); // set author manually

        // Save book
        Books saved = bookRepository.save(book);

        // Map response manually (or use ModelMapper if safe)
        BooksResponse response = new BooksResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setStock(saved.getStock());
        response.setAuthorName(saved.getAuthor().getName());
       // response.setAuthorId(saved.getAuthor().getId());

        return response;
    }

    // READ ALL
    public List<BooksResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BooksResponse.class))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public BooksResponse getBookById(Long id) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return modelMapper.map(book, BooksResponse.class);
    }

    // UPDATE
    public BooksResponse updateBook(Long id, BooksRequest dto) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setStock(dto.getStock());
        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);
        }
        Books updated = bookRepository.save(book);
        return modelMapper.map(updated, BooksResponse.class);
    }

    // DELETE
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // BORROW BOOK - Transactional operation
    @Transactional
    public void borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() < 1) {
            throw new RuntimeException("Book out of stock — rolling back transaction");
        }

        book.setStock(book.getStock() - 1);
        member.getBorrowedbooks().add(book);

        // Both save under transaction
        bookRepository.save(book);
        memberRepository.save(member);
    }
}