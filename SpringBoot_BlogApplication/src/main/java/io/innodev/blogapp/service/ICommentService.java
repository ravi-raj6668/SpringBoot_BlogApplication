package io.innodev.blogapp.service;

import io.innodev.blogapp.payloads.CommentDTO;

public interface ICommentService {
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    public void deleteComment(Integer commentId);
}
