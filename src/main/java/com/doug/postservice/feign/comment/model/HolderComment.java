package com.doug.postservice.feign.comment.model;

import lombok.Data;

@Data
public class HolderComment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
