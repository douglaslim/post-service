package com.doug.postservice.feign.post;

import com.doug.postservice.feign.post.model.HolderPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "post-client", url = "https://jsonplaceholder.typicode.com/posts")
public interface PostClient {

    @GetMapping("/")
    List<HolderPost> getAllPosts();

    @GetMapping("/{postId}")
    HolderPost getPostById(@PathVariable("postId") int postId);
}
