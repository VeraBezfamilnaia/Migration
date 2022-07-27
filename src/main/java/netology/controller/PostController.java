package netology.controller;

import netology.model.Post;
import netology.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Set<Post> all() {
        return service.all();
    }

    @GetMapping("{/id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("{/id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}