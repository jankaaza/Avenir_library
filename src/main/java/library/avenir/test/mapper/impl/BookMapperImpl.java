package library.avenir.test.mapper.impl;

import library.avenir.test.dto.book.BookDto;
import library.avenir.test.entity.Book;
import library.avenir.test.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookMapperImpl implements BookMapper {

//    private final AuthorMapper authorMapper;

//    public BookMapperImpl(AuthorMapper authorMapper) {
//        this.authorMapper = authorMapper;
//    }

    @Override
    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setSummary(book.getSummary());
        bookDto.setIsbn(book.getIsbn());
        return bookDto;
    }
}
