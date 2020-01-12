package com.xml.parser.post.analyse;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostAnalyseDetails {

    private final LocalDateTime firstPost;
    private final LocalDateTime lastPost;
    private final int totalPosts;
    private final long totalAcceptedPosts;
    private final double avgScore;
}
