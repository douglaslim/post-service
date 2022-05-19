package com.doug.postservice.controller;

import com.doug.postservice.model.Post;
import com.doug.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping()
    public ResponseEntity<List<Post>> getTopPosts() {
        List<Post> topPosts = postService.retrieveTopPosts();
        return ResponseEntity.ok(topPosts);
    }
}
