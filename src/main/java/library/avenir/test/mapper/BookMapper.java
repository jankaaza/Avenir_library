package library.avenir.test.mapper;

import library.avenir.test.dto.book.BookDto;
import library.avenir.test.entity.Book;

public interface BookMapper {
    BookDto toDto(Book book);
}
