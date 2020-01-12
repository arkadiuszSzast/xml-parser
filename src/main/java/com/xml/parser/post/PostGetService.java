package com.xml.parser.post;

import com.xml.parser.post.analyse.AnalyseParams;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class PostGetService {

    private final WebClient webClient;

    public WebClient.ResponseSpec getPosts(AnalyseParams analyseParams) {
        return webClient.get()
                .uri(analyseParams.getUrl())
                .accept(MediaType.APPLICATION_XML)
                .retrieve();
    }
}
