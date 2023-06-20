package com.epam.blog.controller;

import com.epam.blog.entity.Post;
import com.epam.blog.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostsController {
    private final PostsService postsService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postsService.createPost(post));
    }

    @PutMapping("/{id}/tags")
    public ResponseEntity<Void> updatePost(@PathVariable Integer id, @RequestBody List<String> tags) {
        postsService.updateTags(id, tags);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postsService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam List<String> tags,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(postsService.getPosts(tags, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable Integer id) {
        return ResponseEntity.ok(postsService.getPost(id));
    }
}
