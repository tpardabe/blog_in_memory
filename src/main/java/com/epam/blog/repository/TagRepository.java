package com.epam.blog.repository;

import com.epam.blog.entity.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {

    Optional<Tag> findByName(String name);

    List<Tag> findByNameIn(List<String> tagName);
}
