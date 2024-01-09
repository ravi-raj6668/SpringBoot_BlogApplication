package io.innodev.blogapp.repository;

import io.innodev.blogapp.entity.Category;
import io.innodev.blogapp.entity.Post;
import io.innodev.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByPostTitleContaining(String postTitle);
}
