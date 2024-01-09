package io.innodev.blogapp.service;

import io.innodev.blogapp.config.ModelMapperUtil;
import io.innodev.blogapp.entity.Comment;
import io.innodev.blogapp.entity.Post;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import io.innodev.blogapp.payloads.CommentDTO;
import io.innodev.blogapp.repository.CommentRepository;
import io.innodev.blogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, ModelMapperUtil modelMapperUtil) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = modelMapperUtil.modelMapper().map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapperUtil.modelMapper().map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        commentRepository.delete(comment);
    }
}
