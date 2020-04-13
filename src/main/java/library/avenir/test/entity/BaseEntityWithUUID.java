package library.avenir.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityWithUUID {

    @Id
    @Type(type = "pg-uuid")
    private UUID id;

    public BaseEntityWithUUID(){
        this.id = UUID.randomUUID();
    }
}
