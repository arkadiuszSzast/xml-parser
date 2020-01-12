package com.xml.parser.utils;

import com.xml.parser.post.domain.Post;

import java.time.LocalDateTime;

public class PostFactory {

    private final static Long DEFAULT_ID = 1L;
    private final static Long DEFAULT_SCORE = 10L;
    private final static Long DEFAULT_PARENT_ID = 20L;
    private final static Long DEFAULT_ACCEPTED_ANSWER_ID = 300L;
    private final static LocalDateTime CREATION_DATE = LocalDateTime.of(2019, 10, 11, 12, 13);

    public static Post build(Long id, LocalDateTime creationDate, Long score, Long parentId, Long acceptedAnswerId) {
        return new Post(id, creationDate, score, parentId, acceptedAnswerId);
    }

    public static Post build() {
        return new Post(DEFAULT_ID, CREATION_DATE, DEFAULT_SCORE, DEFAULT_PARENT_ID, DEFAULT_ACCEPTED_ANSWER_ID);
    }

    public static Post build(Long score) {
        return build(DEFAULT_ID, CREATION_DATE, score, DEFAULT_PARENT_ID, DEFAULT_ACCEPTED_ANSWER_ID);
    }

    public static Post build(LocalDateTime creationDate) {
        return build(DEFAULT_ID, creationDate, DEFAULT_SCORE, DEFAULT_PARENT_ID, DEFAULT_ACCEPTED_ANSWER_ID);
    }

    public static Post build(Long score, LocalDateTime creationDate) {
        return build(DEFAULT_ID, creationDate, score, DEFAULT_PARENT_ID, DEFAULT_ACCEPTED_ANSWER_ID);
    }

    public static Post buildWithoutAcceptedAnswer() {
        return new Post(DEFAULT_ID, CREATION_DATE, DEFAULT_SCORE, DEFAULT_PARENT_ID, null);
    }
}
