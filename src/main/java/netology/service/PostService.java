package netology.service;

import netology.exception.NotFoundException;
import netology.model.Post;
import netology.repository.PostRepository;

import java.util.Set;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Set<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}