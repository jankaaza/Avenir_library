package library.avenir.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import library.avenir.test.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role extends BaseEntity
{
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name", length = 60)
    private RoleName roleName;
}