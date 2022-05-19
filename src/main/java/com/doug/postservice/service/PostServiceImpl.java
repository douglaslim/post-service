package com.doug.postservice.service;

import com.doug.postservice.feign.comment.CommentClient;
import com.doug.postservice.feign.comment.model.HolderComment;
import com.doug.postservice.feign.post.PostClient;
import com.doug.postservice.feign.post.model.HolderPost;
import com.doug.postservice.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostClient postClient;
    private final CommentClient commentClient;

    @Override
    public List<Post> retrieveTopPosts() {
        List<HolderPost> holderPosts = postClient.getAllPosts();
        List<HolderComment> holderComments = commentClient.getAllComments();

        if(holderPosts.size() <= 0 ) {
            return Collections.emptyList();
        }
        Map<Integer, Post> postIdToPost = holderPosts
                .stream()
                .collect(Collectors.toMap(HolderPost::getId, item -> new Post(item.getId(), item.getTitle(), item.getBody(), 0)));
        for (HolderComment holderComment: holderComments) {
            Post post = postIdToPost.get(holderComment.getPostId());
            if (post != null) {
                post.setTotalNumberOfComments(post.getTotalNumberOfComments() + 1);
                postIdToPost.put(post.getPostId(), post);
            }
        }
        List<Post> topPosts = new ArrayList<>(postIdToPost.values());
        topPosts.sort(new PriceComparator());
        return topPosts;
    }

    static class PriceComparator implements Comparator<Post> {
        @Override
        public int compare(Post o1, Post o2) {
            return Integer.compare(o2.getTotalNumberOfComments(), o1.getTotalNumberOfComments());
        }
    }
}
