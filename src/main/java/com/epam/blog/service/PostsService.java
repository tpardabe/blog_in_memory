package com.epam.blog.service;

import com.epam.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostsService {
    Post createPost(Post post);

    void updateTags(Integer id, List<String> tags);

    void deletePost(Integer id);

    List<Post> getPosts(List<String> tags, Integer page, Integer size);

    Optional<Post> getPost(Integer id);
}
