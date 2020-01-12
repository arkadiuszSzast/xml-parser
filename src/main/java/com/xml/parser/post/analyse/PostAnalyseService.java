package com.xml.parser.post.analyse;

import com.xml.parser.post.PostGetService;
import com.xml.parser.post.PostsWrapper;
import com.xml.parser.utils.ThrowingFunction;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Service
@AllArgsConstructor
public class PostAnalyseService {

    private final PostGetService postGetService;

    public Mono<PostAnalyse> getStatistics(AnalyseParams analyseParams) throws JAXBException {
        var jaxbContext = JAXBContext.newInstance(PostsWrapper.class);
        var jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return postGetService.getPosts(analyseParams)
                .bodyToMono(ByteArrayResource.class)
                .map(ThrowingFunction.unchecked(ByteArrayResource::getInputStream))
                .map(ThrowingFunction.unchecked(jaxbUnmarshaller::unmarshal))
                .cast(PostsWrapper.class)
                .map(PostsWrapper::makeAnalyse);
    }

}

