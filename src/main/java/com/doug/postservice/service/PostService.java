package com.doug.postservice.service;

import com.doug.postservice.model.Post;

import java.util.List;

public interface PostService {
    List<Post> retrieveTopPosts();
}
