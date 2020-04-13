package library.avenir.test.controller;

import library.avenir.test.dto.book.BookCreateDto;
import library.avenir.test.dto.book.BookDto;
import library.avenir.test.dto.book.BookInstanceCreateDto;
import library.avenir.test.dto.book.ReserveBookDto;
import library.avenir.test.entity.Book;
import library.avenir.test.entity.BookInstance;
import library.avenir.test.entity.Genre;
import library.avenir.test.entity.Language;
import library.avenir.test.filterrequest.book.BookFilterRequest;
import library.avenir.test.repository.BookRepository;
import library.avenir.test.repository.GenreRepository;
import library.avenir.test.repository.LanguageRepository;
import library.avenir.test.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;

    @GetMapping
    private List<BookDto> getAllBooks() {
        return bookService.findAll();
    }
    
    @GetMapping("/{id}")
    private List<Book> getAllById(@PathVariable Long id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @PostMapping("/search")
    private Page<BookDto> search(@RequestBody BookFilterRequest filterRequest) {
        return bookService.search(filterRequest);
    }
    
    @PostMapping
    private BookDto save(@RequestBody BookCreateDto dto)
    {
    	return bookService.save(dto);
    }
    
    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id)
    {
    	bookService.delete(id);
    }

    @GetMapping("/languages")
    private List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    @GetMapping("/genres")
    private List<Genre> getAllGenres()
    {
        return genreRepository.findAll();
    }

    @PostMapping("/addLanguage")
    private Language addLanguage(@RequestBody Language language)
    {
        return languageRepository.save(language);
    }

    @PostMapping("/addGenre")
    private Genre addGenre(@RequestBody Genre genre)
    {
        return genreRepository.save(genre);
    }

    @PostMapping("/addBookInstance")
    private BookInstance addBookInstance(@RequestBody BookInstanceCreateDto dto)
    {
        return bookService.addBookInstance(dto);
    }

    @GetMapping("/listBookInstances")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookInstance> getAllBookInstances(){
        return bookService.getAllBookInstances();
    }

    @PostMapping("/reserve")
    public void reserveBook(@RequestBody ReserveBookDto dto)
    {
        bookService.reserveBook(dto);
    }
}
