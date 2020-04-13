package library.avenir.test.mapper.impl;

import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.dto.book.BookDto;
import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;
import library.avenir.test.mapper.AuthorMapper;
import library.avenir.test.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorMapperImpl implements AuthorMapper {

    private final BookMapper bookMapper;

    public AuthorMapperImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public AuthorDto toAuthorDto(Author author, List<Book> books) {
        List<BookDto> bookDtos = books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        AuthorDto dto = new AuthorDto();
        dto.setBiography(author.getBiography());
        dto.setBirthDate(author.getBirthDate());
        dto.setDeathDate(author.getDeathDate());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBooks(bookDtos);
        dto.setId(author.getId());
        return dto;
    }
    
    @Override
    public AuthorDto toAuthorDtoNoBook(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setBiography(author.getBiography());
        dto.setBirthDate(author.getBirthDate());
        dto.setDeathDate(author.getDeathDate());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setId(author.getId());
        return dto;
    }
}
