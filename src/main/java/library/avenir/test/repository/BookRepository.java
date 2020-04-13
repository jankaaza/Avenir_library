package library.avenir.test.repository;

import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByAuthorId(Long id);
    List<Book> findAllByTitleContainsIgnoreCase(String searchString);
}
