package com.epam.blog.service.impl;

import com.epam.blog.entity.Post;
import com.epam.blog.entity.Tag;
import com.epam.blog.repository.PostsRepository;
import com.epam.blog.repository.TagRepository;
import com.epam.blog.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final TagRepository tagRepository;

    @Transactional
    public Post createPost(Post postDto) {
        return postsRepository.save(postDto);
    }

    @Override
    public void updateTags(Integer id, List<String> tags) {
        var post = postsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post Not Found"));
        List<Tag> collect = tags.stream().map(name -> {
            var tag = new Tag();
            tag.setName(name);
            return tag;
        }).collect(Collectors.toList());
        post.setTags(collect);
        postsRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) {
        postsRepository.deleteById(id);
    }

    @Override
    public List<Post> getPosts(List<String> tags, Integer page, Integer size) {
        List<Integer> tagIds = tagRepository.findByNameIn(tags).stream().map(Tag::getId).collect(Collectors.toList());
        return postsRepository.findPostsByTagsIdIn(tagIds, PageRequest.of(page, size));
    }

    @Override
    public Optional<Post> getPost(Integer id) {
        return postsRepository.findById(id);
    }
}
