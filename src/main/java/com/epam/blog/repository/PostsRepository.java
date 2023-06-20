package com.epam.blog.repository;

import com.epam.blog.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends PagingAndSortingRepository<Post, Integer> {

    List<Post> findPostsByTagsIdIn(Iterable<Integer> tags, Pageable pageable);

    List<Post> findPostsByTagsId(Integer tagId);
}
