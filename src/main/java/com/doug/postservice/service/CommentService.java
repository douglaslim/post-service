package com.doug.postservice.service;

import com.doug.postservice.feign.comment.model.HolderComment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<HolderComment> getFilteredComments(Map<String, String> filterParams);
}
