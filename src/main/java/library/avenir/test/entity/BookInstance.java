package library.avenir.test.entity;

import library.avenir.test.enums.StatusName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_instances")
@Getter
@Setter
public class BookInstance extends BaseEntityWithUUID {

    @Column(name = "due_back")
    private LocalDate dueBack;

    @Column(length = 60)
    private StatusName status;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
