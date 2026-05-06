package com.example.likelionjpaswagger.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

    private String title;

    private String content;
}