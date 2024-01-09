package io.innodev.blogapp.controller;

import io.innodev.blogapp.config.Constant;
import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.payloads.PostDTO;
import io.innodev.blogapp.payloads.PostResponse;
import io.innodev.blogapp.service.FileService;
import io.innodev.blogapp.service.IPostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {

    private final IPostService postService;
    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    public PostController(IPostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
    }

    //create
    @PostMapping(value = "/createPost/user/{userId}/category/{categoryId}/postCreated",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDTO createPOst = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createPOst, HttpStatus.CREATED);
    }

    //get post by user
    @GetMapping(value = "/getPostByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId) {
        List<PostDTO> postByUser = postService.getPostByUser(userId);
        return new ResponseEntity<>(postByUser, HttpStatus.OK);
    }


    //get post by category
    @GetMapping(value = "/getPostByCategory/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> postByCategory = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postByCategory, HttpStatus.OK);
    }

    //get All Posts
    @GetMapping(value = "/getAllPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = Constant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = Constant.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortOrder", defaultValue = Constant.SORT_ORDER, required = false) String sortOrder) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getPostById/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId) {
        PostDTO post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<Message> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new Message("Post deleted successfully : " + postId, true, new Date().toString()), HttpStatus.OK);
    }

    @PostMapping("/updatePost/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //searching api
    @GetMapping("/searchPost/{value}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable String value) {
        List<PostDTO> postDTOList = postService.searchPosts(value);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping("/uploadImage/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam(value = "image") MultipartFile file, @PathVariable Integer postId) throws IOException {
        PostDTO postDTO = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, file);
        postDTO.setImage(fileName);
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadImage/{image}", produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(@PathVariable("image") String image, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = fileService.getResource(path, image);
        httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());

    }
}
