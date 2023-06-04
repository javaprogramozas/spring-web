package hu.bearmaster.springtutorial.web.service;

import hu.bearmaster.springtutorial.web.model.Post;
import hu.bearmaster.springtutorial.web.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }
}
