package com.example.likelionjpaswagger.post;

import com.example.likelionjpaswagger.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 게시글 작성
    @PostMapping
    public Post createPost(
            @RequestBody PostRequest request,
            @RequestHeader("Authorization") String authorizationHeader
    ) {

        String token =
                authorizationHeader.replace("Bearer ", "");

        String author =
                jwtTokenProvider.getEmail(token);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    // 전체 게시글 조회
    @GetMapping
    public List<Post> getPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // Query Method 검색
    @GetMapping("/search")
    public List<Post> searchPosts(
            @RequestParam String keyword
    ) {

        return postRepository.findByTitleContaining(keyword);
    }

    // 작성자 기준 조회
    @GetMapping("/author")
    public List<Post> getPostsByAuthor(
            @RequestParam String author
    ) {

        return postRepository.findByAuthor(author);
    }

    // 최신 게시글 3개 조회
    @GetMapping("/top3")
    public List<Post> getTop3Posts() {

        return postRepository.findTop3ByOrderByCreatedAtDesc();
    }

    // @Query Annotation 사용
    @GetMapping("/query-search")
    public List<Post> querySearchPosts(
            @RequestParam String keyword
    ) {

        return postRepository.searchByTitle(keyword);
    }
}