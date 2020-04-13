package library.avenir.test.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdentityAvailabilityDto {

    private Boolean available;

    public UserIdentityAvailabilityDto(Boolean available) {
        this.available = available;
    }

}
