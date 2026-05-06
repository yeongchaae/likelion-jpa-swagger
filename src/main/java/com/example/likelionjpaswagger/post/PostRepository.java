package com.example.likelionjpaswagger.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Query Method
    List<Post> findByTitleContaining(String keyword);

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findByAuthor(String author);

    List<Post> findTop3ByOrderByCreatedAtDesc();

    // @Query Annotation
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> searchByTitle(@Param("keyword") String keyword);
}