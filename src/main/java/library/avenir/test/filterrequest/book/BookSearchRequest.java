package library.avenir.test.filterrequest.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BookSearchRequest {
    @NotNull
    @NotBlank
    private String searchString;

    @NotEmpty
    private List<Long> authorIds;

    @NotEmpty
    private List<String> genres;
}
