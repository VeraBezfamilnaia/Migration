package netology.repository;

import netology.model.Post;
import netology.validator.PostValidator;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final static boolean REMOVED_POST_VALUE = true;
    private final static int NEW_POST_INDEX = 0;
    private final static int START_POST_INDEX = 1;
    private final Map<Post, AtomicInteger> posts = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(START_POST_INDEX);

    public Set<Post> all() {
        return getPosts();
    }

    public Optional<Post> getById(long id) {
        for (var post : getPosts()) {
            if (post.getId() == id && !post.getIsRemoved()) {
                return Optional.of(post);
            }
            break;
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == NEW_POST_INDEX) {
            addNewPost(post);
        } else {
            updatePost(post);
        }
        return post;
    }

    public void removeById(long id) {
        var currentPosts = getPosts();
        var post = PostValidator.validate(currentPosts, id);
        post.setRemoved(REMOVED_POST_VALUE);
    }

    private void addNewPost(Post post) {
        post.setId(counter.getAndIncrement());
        posts.put(post, counter);
    }

    private void updatePost(Post post) {
        var currentPosts = getPosts();
        var existingPost = PostValidator.validate(currentPosts, post);
        existingPost.setContent(post.getContent());
    }

    private Set<Post> getPosts() {
        return posts.keySet().stream()
                .filter(post -> !post.getIsRemoved())
                .collect(Collectors.toSet());
    }
}