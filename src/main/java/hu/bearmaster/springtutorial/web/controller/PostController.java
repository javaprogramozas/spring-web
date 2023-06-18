package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.model.Post;
import hu.bearmaster.springtutorial.web.model.request.CreatePostRequest;
import hu.bearmaster.springtutorial.web.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/post/{id}")
    public Post getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id).orElse(null);
        return post;
    }

    @PostMapping(value = "/post")
    public Post createNewPost(@RequestBody CreatePostRequest request) {
        return postService.createNewPost(request);
    }
}
