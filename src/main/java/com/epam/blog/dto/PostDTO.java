package com.epam.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private String title;
    private String content;

    private List<String> tags;
}
