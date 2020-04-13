package library.avenir.test.service;

import com.querydsl.core.BooleanBuilder;

import library.avenir.test.dto.book.BookCreateDto;
import library.avenir.test.dto.book.BookDto;
import library.avenir.test.dto.book.BookInstanceCreateDto;
import library.avenir.test.dto.book.ReserveBookDto;
import library.avenir.test.entity.*;
import library.avenir.test.enums.StatusName;
import library.avenir.test.filterrequest.book.BookFilterRequest;
import library.avenir.test.mapper.BookMapper;
import library.avenir.test.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final UserRepository userRepository;

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Book> findAllById(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    public Book updateAuthor(Book book, Author author) {
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    public Page<BookDto> search(BookFilterRequest filterRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        /*
        if (filterRequest.getSearchRequest().getGenres() != null) {
            predicate.and(QBook.book.category.in(filterRequest.getSearchRequest().getCategories()));
        }
         */

        if (filterRequest.getSearchRequest().getAuthorIds() != null) {
            predicate.and(QBook.book.author.id.in(filterRequest.getSearchRequest().getAuthorIds()));
        }

        if (filterRequest.getSearchRequest().getSearchString() != null &&
                !filterRequest.getSearchRequest().getSearchString().isBlank()) {
            predicate.and(QBook.book.title.containsIgnoreCase(filterRequest.getSearchRequest().getSearchString()));
        }

        Integer size = filterRequest.getPageRequest().getSize();
        Integer pageNumber = filterRequest.getPageRequest().getPageNumber();
        PageRequest page = PageRequest.of(pageNumber, size);

        return bookRepository.findAll(predicate, page)
                .map(bookMapper::toDto);
    }

	public BookDto save(BookCreateDto createDto) {
		Author author = authorRepository.findById(createDto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("No author with id " + createDto.getAuthorId()));

        Language language = languageRepository.findById(createDto.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("No language with id " + createDto.getLanguageId()));

		List<Genre> genres = genreRepository.findAllById(createDto.getGenreIds());
		Book book = new Book();
		book.setTitle(createDto.getTitle());
		book.setSummary(createDto.getSummary());
		book.setIsbn(createDto.getIsbn());
		book.setAuthor(author);
		book.setLanguage(language);
		book.setGenres(genres);
		bookRepository.save(book);
		return bookMapper.toDto(book);
	}

	public void delete(Long id) {
		Book deletingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Book with id " + id));
        bookRepository.delete(deletingBook);
		
	}

	public BookInstance addBookInstance(BookInstanceCreateDto dto)
    {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("There is no Book with id " + dto.getBookId()));
        BookInstance instance = new BookInstance();
        instance.setDueBack(dto.getDueBack());
        instance.setStatus(dto.getStatus());
        instance.setBook(book);
        return bookInstanceRepository.save(instance);
    }

    public List<BookInstance> getAllBookInstances() {
        return bookInstanceRepository.findAll();
    }

    public void reserveBook(ReserveBookDto dto) {
        BookInstance instance = bookInstanceRepository.findById(dto.getBookInstanceId())
                .orElseThrow(() -> new EntityNotFoundException("There is no BookInstance with id " + dto.getBookInstanceId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("There is no User with id " + dto.getUserId()));

        instance.setUser(user);
        instance.setDueBack(dto.getDueBack());
        instance.setStatus(StatusName.ON_LOAN);
        bookInstanceRepository.save(instance);
    }

    public List<BookInstance> getMyBooks(Long userId) {
        return bookInstanceRepository.findAllByUserId(userId);
    }

    public List<BookInstance> getUsersBooks(String username) {
        return bookInstanceRepository.getAllByUserName(username);
    }
}