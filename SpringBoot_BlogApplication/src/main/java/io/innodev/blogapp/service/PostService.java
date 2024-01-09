package io.innodev.blogapp.service;

import io.innodev.blogapp.config.ModelMapperUtil;
import io.innodev.blogapp.entity.Category;
import io.innodev.blogapp.entity.Post;
import io.innodev.blogapp.entity.User;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import io.innodev.blogapp.payloads.PostDTO;
import io.innodev.blogapp.payloads.PostResponse;
import io.innodev.blogapp.repository.CategoryRepository;
import io.innodev.blogapp.repository.PostRepository;
import io.innodev.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService {

    private static final String POST_ID = "post_id";
    private final PostRepository postRepository;
    private final ModelMapperUtil modelMapperUtil;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapperUtil modelMapperUtil, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapperUtil = modelMapperUtil;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post post = modelMapperUtil.modelMapper().map(postDTO, Post.class);
        post.setUser(user);
        post.setCategory(category);
        post.setAddedDate(new Date());
        post.setImage("default.img");
        Post createPost = postRepository.save(post);
        return modelMapperUtil.modelMapper().map(createPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", POST_ID, postId));
        post.setPostTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());

        Post updatedPost = postRepository.save(post);
        return modelMapperUtil.modelMapper().map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", POST_ID, postId));
        postRepository.delete(post);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> postList = pagePost.getContent();

        List<PostDTO> postDTOList = postList.stream().map(post -> modelMapperUtil.modelMapper().map(post, PostDTO.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalEle(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", POST_ID, postId));
        return modelMapperUtil.modelMapper().map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> postList = postRepository.findByCategory(category);
        return postList.stream().map((post -> modelMapperUtil.modelMapper().map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> postList = postRepository.findByUser(user);
        return postList.stream().map((post -> modelMapperUtil.modelMapper().map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> searchPosts(String value) {
        List<Post> postList = postRepository.findByPostTitleContaining(value);
        return postList.stream().map((post -> modelMapperUtil.modelMapper().map(post, PostDTO.class))).toList();
    }
}
