package library.avenir.test.filterrequest.author;

import library.avenir.test.filterrequest.common.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorFilterRequest {
    private AuthorSearchRequest searchRequest;
    private PageRequest pageRequest;
}
