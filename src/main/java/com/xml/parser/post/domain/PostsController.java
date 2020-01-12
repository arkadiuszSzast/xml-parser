package com.xml.parser.post.domain;

import com.xml.parser.post.analyse.AnalyseParams;
import com.xml.parser.post.analyse.PostAnalyse;
import com.xml.parser.post.analyse.PostAnalyseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

@RestController
@AllArgsConstructor
public class PostsController {

    private final PostAnalyseService postAnalyseService;

    @PostMapping("/analyze")
    public Mono<PostAnalyse> getPostWrapper(@RequestBody @Valid AnalyseParams analyseParams) throws JAXBException {
        return postAnalyseService.getStatistics(analyseParams);
    }
}
