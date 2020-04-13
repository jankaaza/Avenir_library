package library.avenir.test.controller;

import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.entity.Author;
import library.avenir.test.filterrequest.author.AuthorFilterRequest;
import library.avenir.test.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<AuthorDto> findAll() {
        return authorService.findAll();
    }

    @PostMapping("/search")
    private Page<AuthorDto> search(@RequestBody AuthorFilterRequest filterRequest) {
        return authorService.search(filterRequest);
    }
    
    @PostMapping
    private AuthorDto save(@RequestBody Author author)
    {
    	return authorService.save(author);
    }
    
    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id)
    {
    	authorService.delete(id);
    }
}
