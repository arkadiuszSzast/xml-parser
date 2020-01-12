package com.xml.parser.post.analyse;

import com.xml.parser.post.PostGetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostAnalyseServiceTest {

    public static final String URL = "https://google.com";
    @Mock
    private PostGetService postGetService;
    @Mock
    private WebClient.ResponseSpec clientResponse;
    @InjectMocks
    private PostAnalyseService postAnalyseService;

    @Test
    @DisplayName("Should return post analyse")
    void shouldReturnPostAnalyse() throws JAXBException, IOException {
        //arrange
        var analyseParams = new AnalyseParams(URL);
        var xmlBytes = this.getClass().getResourceAsStream("/xml/example_1.xml").readAllBytes();
        when(postGetService.getPosts(analyseParams)).thenReturn(clientResponse);
        when(clientResponse.bodyToMono(ByteArrayResource.class)).thenReturn(Mono.just(new ByteArrayResource(xmlBytes)));

        //act
        var postAnalyse = postAnalyseService.getStatistics(analyseParams).block();

        //assert
        verify(postGetService).getPosts(analyseParams);
        assertAll(
                () -> assertThat(postAnalyse.getAnalyseDate()).isEqualToIgnoringSeconds(LocalDateTime.now()),
                () -> assertThat(postAnalyse.getDetails().getTotalPosts()).isEqualTo(80),
                () -> assertThat(postAnalyse.getDetails().getTotalAcceptedPosts()).isEqualTo(7),
                () -> assertThat(postAnalyse.getDetails().getAvgScore()).isEqualTo(2.975d),
                () -> assertThat(postAnalyse.getDetails().getFirstPost()).isEqualToIgnoringHours(LocalDateTime.of(2015, 7, 14, 0, 0)),
                () -> assertThat(postAnalyse.getDetails().getLastPost()).isEqualToIgnoringHours(LocalDateTime.of(2015, 9, 14, 0, 0))
        );
    }

}