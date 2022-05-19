package com.doug.postservice.feign.post.model;

import lombok.Data;

@Data
public class HolderPost {
    private int userId;
    private int id;
    private String title;
    private String body;
}
