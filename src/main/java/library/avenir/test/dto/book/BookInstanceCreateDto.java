package library.avenir.test.dto.book;

import library.avenir.test.enums.StatusName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class BookInstanceCreateDto {

    private LocalDate dueBack;

    @NotBlank
    private StatusName status;

    @NotBlank
    private Long bookId;

}
