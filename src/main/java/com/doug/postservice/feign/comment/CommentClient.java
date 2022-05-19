package com.doug.postservice.feign.comment;

import com.doug.postservice.feign.comment.model.HolderComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "comment-client", url = "https://jsonplaceholder.typicode.com/comments")
public interface CommentClient {

    @GetMapping("/")
    List<HolderComment> getAllComments();

}
