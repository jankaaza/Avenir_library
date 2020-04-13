package library.avenir.test.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ReserveBookDto {

    private UUID bookInstanceId;

    private Long userId;

    private LocalDate dueBack;

}
