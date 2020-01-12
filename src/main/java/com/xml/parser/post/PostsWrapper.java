package com.xml.parser.post;

import com.xml.parser.post.analyse.PostAnalyse;
import com.xml.parser.post.analyse.PostAnalyseDetails;
import com.xml.parser.post.domain.Post;
import com.xml.parser.utils.exception.PostDateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@XmlRootElement(name="posts")
@NoArgsConstructor
@AllArgsConstructor
public class PostsWrapper {

    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    @XmlElement(name = "row")
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public PostAnalyse makeAnalyse() {
        var firstPostDate = getFirstPostDate();
        var lastPostDate = getLastPostDate();
        var totalPostsCount = posts.size();
        var totalAcceptedPostsCount = getTotalAcceptedPostsCount();
        var avgScore = getAvgScore();
        var postAnalyseDetails = new PostAnalyseDetails(firstPostDate, lastPostDate, totalPostsCount,
                totalAcceptedPostsCount, avgScore);
        return new PostAnalyse(LocalDateTime.now(), postAnalyseDetails);
    }

    private double getAvgScore() {
        return posts.stream()
                    .mapToLong(Post::getScore)
                    .average()
                    .orElse(Double.NaN);
    }

    private long getTotalAcceptedPostsCount() {
        return posts.stream()
                    .map(Post::getAcceptedAnswerId)
                    .filter(Objects::nonNull)
                    .count();
    }

    private LocalDateTime getLastPostDate() {
        return posts.stream()
                    .max(Comparator.comparing(Post::getCreationDate))
                    .map(Post::getCreationDate)
                    .orElseThrow(PostDateNotFoundException::new);
    }

    private LocalDateTime getFirstPostDate() {
        return posts.stream()
                    .min(Comparator.comparing(Post::getCreationDate))
                    .map(Post::getCreationDate)
                    .orElseThrow(PostDateNotFoundException::new);
    }
}
