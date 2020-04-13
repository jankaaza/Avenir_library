package library.avenir.test.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String summary;
    private String isbn;

}
