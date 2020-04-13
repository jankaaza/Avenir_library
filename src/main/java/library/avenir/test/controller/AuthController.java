package library.avenir.test.controller;

import library.avenir.test.dto.user.JwtAuthResponseDto;
import library.avenir.test.dto.user.SignInDto;
import library.avenir.test.dto.user.SignUpDto;
import library.avenir.test.entity.Role;
import library.avenir.test.entity.User;
import library.avenir.test.enums.RoleName;
import library.avenir.test.repository.RoleRepository;
import library.avenir.test.repository.UserRepository;
import library.avenir.test.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInDto dto)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsernameOrEmail(),
                        dto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponseDto(jwt));
    }

    @PostMapping("/signup")
    private User registerUser(@RequestBody SignUpDto dto)
    {
        if(userRepository.existsByUserName(dto.getUserName())) {
            throw new EntityNotFoundException("Username is already taken: " + dto.getUserName());
        }

        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new EntityNotFoundException("Email is already taken: " + dto.getEmail());
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = roleRepository.findByRoleName(RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("There is no role with name " + RoleName.ROLE_USER));
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }
}
