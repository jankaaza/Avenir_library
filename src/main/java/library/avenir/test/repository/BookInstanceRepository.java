package library.avenir.test.repository;

import library.avenir.test.entity.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookInstanceRepository extends JpaRepository<BookInstance, UUID> {

    List<BookInstance> findAllByUserId(Long id);

    @Query("select b from BookInstance b where b.user.userName = :username")
    List<BookInstance> getAllByUserName(@Param("username") String user_name);
}
