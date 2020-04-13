package library.avenir.test.controller;

import library.avenir.test.dto.user.UserIdentityAvailabilityDto;
import library.avenir.test.dto.user.UserSummaryDto;
import library.avenir.test.entity.BookInstance;
import library.avenir.test.entity.User;
import library.avenir.test.repository.UserRepository;
import library.avenir.test.security.UserPrincipal;
import library.avenir.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserSummaryDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)auth.getPrincipal();
        UserSummaryDto userSummary = new UserSummaryDto(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailabilityDto checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUserName(username);
        return new UserIdentityAvailabilityDto(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailabilityDto checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailabilityDto(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserSummaryDto getUserSummary(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username" + username));

        UserSummaryDto userSummary = new UserSummaryDto(user.getId(), user.getUserName(), user.getName());
        return userSummary;
    }

    @GetMapping("/user/my_books")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookInstance> getMyBooks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)auth.getPrincipal();
        return bookService.getMyBooks(currentUser.getId());
    }

    @GetMapping("/users/{username}/borrowed_books")
    public List<BookInstance> getUsersBooks(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username" + username));

        return bookService.getUsersBooks(username);
    }

}
