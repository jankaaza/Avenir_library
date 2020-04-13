package library.avenir.test.filterrequest.book;

import library.avenir.test.filterrequest.common.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFilterRequest {
    private PageRequest pageRequest;
    private BookSearchRequest searchRequest;
}
