package com.doug.postservice.controller;

import com.doug.postservice.feign.comment.model.HolderComment;
import com.doug.postservice.feign.post.model.HolderPost;
import com.doug.postservice.model.Post;
import com.doug.postservice.service.CommentService;
import com.doug.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<HolderComment>> getFilteredComments(@RequestParam Map<String, String> filterParams) {
        List<HolderComment> filteredComments = new ArrayList<>();
        for(Map.Entry<String, String> entry: filterParams.entrySet()){
            try {
                HolderComment.class.getDeclaredField(entry.getKey());
            } catch (NoSuchFieldException e) {
                return ResponseEntity.badRequest().body(filteredComments);
            }
        }
        filteredComments = commentService.getFilteredComments(filterParams);
        return ResponseEntity.ok(filteredComments);
    }
}
