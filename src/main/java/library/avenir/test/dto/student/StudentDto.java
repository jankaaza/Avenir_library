package library.avenir.test.dto.student;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String universityId;
}
