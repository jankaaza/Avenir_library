package library.avenir.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class Language extends BaseEntity {

    @NotBlank
    @Size(max = 40)
    private String name;

}
