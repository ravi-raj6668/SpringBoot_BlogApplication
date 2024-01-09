package io.innodev.blogapp.service;

import io.innodev.blogapp.payloads.PostDTO;
import io.innodev.blogapp.payloads.PostResponse;

import java.util.List;

public interface IPostService {

    //create post
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    //update
    public PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete
    public void deletePost(Integer postId);

    //getAllPost
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    //get one post only
    public PostDTO getPostById(Integer postId);

    //getAll post by category
    public List<PostDTO> getPostByCategory(Integer categoryId);

    //get post by user
    public List<PostDTO> getPostByUser(Integer userId);

    //search  post
    public List<PostDTO> searchPosts(String value);
}
