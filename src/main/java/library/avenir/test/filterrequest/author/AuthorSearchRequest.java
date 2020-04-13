package library.avenir.test.filterrequest.author;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorSearchRequest {
    private String searchString;
    private LocalDate birthDateMin;
    private LocalDate birthDateMax;
}
