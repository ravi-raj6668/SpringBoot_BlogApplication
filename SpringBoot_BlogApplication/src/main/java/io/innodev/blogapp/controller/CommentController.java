package io.innodev.blogapp.controller;

import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.payloads.CommentDTO;
import io.innodev.blogapp.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/postComment/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId) {
        CommentDTO createdComment = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteMapping/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new Message("Comment delete successfully : " + commentId, true, new Date().toString()), HttpStatus.OK);
    }
}
