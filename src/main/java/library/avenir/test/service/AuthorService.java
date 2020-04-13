package library.avenir.test.service;

import com.querydsl.core.BooleanBuilder;
import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;
import library.avenir.test.entity.QAuthor;
import library.avenir.test.filterrequest.author.AuthorFilterRequest;
import library.avenir.test.mapper.AuthorMapper;
import library.avenir.test.repository.AuthorRepository;
import library.avenir.test.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;

    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> dtos = new ArrayList<>();
        for (Author author : authors) {
            List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
            AuthorDto authorDto = authorMapper.toAuthorDto(author, booksByAuthor);
            dtos.add(authorDto);
        }
        return dtos;
    }

    public Page<AuthorDto> search(AuthorFilterRequest filterRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (filterRequest.getSearchRequest().getSearchString() != null) {
            predicate.andAnyOf(QAuthor.author.firstName.containsIgnoreCase(
                    filterRequest.getSearchRequest().getSearchString()),
                    QAuthor.author.lastName.containsIgnoreCase(
                            filterRequest.getSearchRequest().getSearchString())
            );
        }

        if (filterRequest.getSearchRequest().getBirthDateMin() != null) {
            predicate.and(QAuthor.author.birthDate.after(
                    filterRequest.getSearchRequest().getBirthDateMin()
            ));
        }

        if (filterRequest.getSearchRequest().getBirthDateMax() != null) {
            predicate.and(QAuthor.author.birthDate.before(
                    filterRequest.getSearchRequest().getBirthDateMax()
            ));
        }

        Integer size = filterRequest.getPageRequest().getSize();
        Integer pageNumber = filterRequest.getPageRequest().getPageNumber();
        PageRequest page = PageRequest.of(pageNumber, size);

        return authorRepository.findAll(predicate, page)
                .map(x -> authorMapper.toAuthorDto(x, bookService.getBooksByAuthor(x)));
    }

    public Author getById(Long id) {
        return authorRepository.getOne(id);
    }

	public AuthorDto save(Author author) {
		authorRepository.save(author);
		return authorMapper.toAuthorDtoNoBook(author);
	}

	@Transactional
	public void delete(Long id) {
		Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Author with id " + id));
		List<Book> books = bookRepository.findAllByAuthorId(id);
		for (Book book: books) {
			bookRepository.delete(book);
		}
		
		authorRepository.delete(author);
	}
}