package library.avenir.test.mapper;

import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;

import java.util.List;

public interface AuthorMapper {
    AuthorDto toAuthorDto(Author author, List<Book> books);
    AuthorDto toAuthorDtoNoBook(Author author);
}
