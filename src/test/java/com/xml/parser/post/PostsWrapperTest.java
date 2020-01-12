package com.xml.parser.post;

import com.xml.parser.utils.PostFactory;
import com.xml.parser.utils.exception.PostDateNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class PostsWrapperTest {

    @Test
    @DisplayName("Should create post analyse form PostWrapper")
    void shouldCratePostAnalyse() {
        //arrange && act
        var lastPostDate = LocalDateTime.of(2020, 1, 1, 1, 1);
        var firstPostDate = LocalDateTime.of(2017, 1, 1, 1, 1);
        var posts = List.of(PostFactory.build(),
                PostFactory.build(1L),
                PostFactory.build(3L),
                PostFactory.build(6L),
                PostFactory.buildWithoutAcceptedAnswer(),
                PostFactory.buildWithoutAcceptedAnswer(),
                PostFactory.build(lastPostDate),
                PostFactory.build(firstPostDate)
        );
        var postsWrapper = new PostsWrapper(posts);
        var postAnalyse = postsWrapper.makeAnalyse();

        //assert
        assertAll(
                () -> assertThat(postAnalyse.getAnalyseDate()).isEqualToIgnoringSeconds(LocalDateTime.now()),
                () -> assertThat(postAnalyse.getDetails().getLastPost()).isEqualTo(lastPostDate),
                () -> assertThat(postAnalyse.getDetails().getFirstPost()).isEqualTo(firstPostDate),
                () -> assertThat(postAnalyse.getDetails().getAvgScore()).isEqualTo(7.5d),
                () -> assertThat(postAnalyse.getDetails().getTotalPosts()).isEqualTo(posts.size()),
                () -> assertThat(postAnalyse.getDetails().getTotalAcceptedPosts()).isEqualTo(6L)
        );
    }

    @Test
    @DisplayName("Should throw an exception when list is empty")
    void shouldThrowExceptionWhenListIsEmpty() {
        //arrange
        var postsWrapper = new PostsWrapper(List.of());

        //act && assert
        assertThrows(PostDateNotFoundException.class, postsWrapper::makeAnalyse);
    }

}