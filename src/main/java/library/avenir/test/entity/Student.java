package library.avenir.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "university_id", unique = true)
    private String universityId;
    
    @ManyToMany
    @JoinTable(
    		name = "students_books",
    		joinColumns = @JoinColumn(name = "student_id"),
    		inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> borrowings;
}
