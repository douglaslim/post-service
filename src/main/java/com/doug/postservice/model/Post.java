package com.doug.postservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Post {

    public Post(int postId, String postTitle, String postBody, int totalNumberOfComments){
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.totalNumberOfComments = totalNumberOfComments;
    }

    @JsonProperty("post_id")
    private int postId;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("post_body")
    private String postBody;

    @JsonProperty("total_number_of_comments")
    private int totalNumberOfComments;
}
