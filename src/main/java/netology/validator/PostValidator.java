package netology.validator;

import netology.exception.NotFoundException;
import netology.model.Post;

import java.util.Map;

public class PostValidator {
    public static Post validate(Map<Long, Post> posts, long id) {
        var existingPost = posts.get(id);
        if (existingPost != null) {
            return existingPost;
        }
        throw new NotFoundException("Post with this id wasn't found");
    }
}
