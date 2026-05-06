package com.example.likelionjpaswagger.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    // 게시글 작성
    @PostMapping
    public Post createPost(
            @RequestBody PostRequest request,
            Authentication authentication
    ) {

        // JWT 토큰에서 로그인 이메일 추출
        String author = authentication.getName();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        // 게시글 저장
        return postRepository.save(post);
    }

    // 전체 게시글 조회 (최신순 정렬)
    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 제목에 특정 키워드가 포함된 게시글 조회 (Query Method)
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    // 특정 작성자가 작성한 게시글 조회
    @GetMapping("/author")
    public List<Post> getPostsByAuthor(@RequestParam String author) {
        return postRepository.findByAuthor(author);
    }

    // 최신 게시글 3개 조회
    @GetMapping("/top3")
    public List<Post> getTop3Posts() {
        return postRepository.findTop3ByOrderByCreatedAtDesc();
    }

    // @Query 어노테이션을 사용한 제목 검색
    @GetMapping("/query-search")
    public List<Post> querySearchPosts(@RequestParam String keyword) {
        return postRepository.searchByTitle(keyword);
    }
}