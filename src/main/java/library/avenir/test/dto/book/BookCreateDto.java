package library.avenir.test.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter

public class BookCreateDto {

    @NotBlank
    private String title;

    private String summary;

    @NotBlank
    @Size(max = 13)
    private String isbn;

    @NotBlank
    private Set<Long> genreIds;

    @NotBlank
    private Long languageId;

    @NotBlank
    private Long authorId;

}
