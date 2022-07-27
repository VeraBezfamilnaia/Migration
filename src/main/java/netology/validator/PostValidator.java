package netology.validator;

import netology.exception.NotFoundException;
import netology.model.Post;

import java.util.Set;

public class PostValidator {
    public static Post validate(Set<Post> posts, Post post) {
        long id = post.getId();
        for (var existingPost : posts) {
            if (existingPost.getId() == id) {
                return existingPost;
            }
        }
        throw new NotFoundException("Post with this id wasn't found");
    }

    public static Post validate(Set<Post> posts, long id) {
        for (var post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        throw new NotFoundException("Post weren't found");
    }
}
