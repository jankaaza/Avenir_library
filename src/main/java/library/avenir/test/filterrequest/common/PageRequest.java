package library.avenir.test.filterrequest.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PageRequest {
    @Max(100)
    @Min(0)
    private Integer size;
    @Min(0)
    private Integer pageNumber;
}
