package library.avenir.test.repository;

import library.avenir.test.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorRepository extends JpaRepository<Author, Long>,
        QuerydslPredicateExecutor<Author> {
}
