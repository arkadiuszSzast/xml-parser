package com.xml.parser.post.analyse;

import com.xml.parser.post.PostGetService;
import com.xml.parser.post.domain.PostsController;
import com.xml.parser.utils.MockWebClientResponse;
import com.xml.parser.utils.XmlAdapterIntegrationTest;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@XmlAdapterIntegrationTest
@WebFluxTest(PostsController.class)
@Import({PostAnalyseService.class, PostGetService.class})
class PostAnalyseServiceTestIT {

    private static final String URL = "https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml";
    public static final String BAD_URL = "https://google.com";

    @MockBean
    WebClient mockWebClient;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should return post analyse")
    void shouldAnalysePosts() throws IOException {
        //arrange
        var analyseParams = new AnalyseParams(URL);
        var xmlBytes = this.getClass().getResourceAsStream("/xml/example_1.xml").readAllBytes();
        MockWebClientResponse.getWebClientMock(mockWebClient, new ByteArrayResource(xmlBytes));

        //act && assert
        var postAnalyse= webClient.post().uri("/analyze")
                .bodyValue(analyseParams)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PostAnalyse.class)
                .returnResult()
                .getResponseBody();

        assertAll(
                () -> assertThat(postAnalyse.getAnalyseDate()).isEqualToIgnoringSeconds(LocalDateTime.now()),
                () -> assertThat(postAnalyse.getDetails().getTotalPosts()).isEqualTo(80),
                () -> assertThat(postAnalyse.getDetails().getTotalAcceptedPosts()).isEqualTo(7),
                () -> assertThat(postAnalyse.getDetails().getAvgScore()).isEqualTo(2.975d),
                () -> assertThat(postAnalyse.getDetails().getFirstPost()).isEqualToIgnoringHours(LocalDateTime.of(2015,7,14,0,0)),
                () -> assertThat(postAnalyse.getDetails().getLastPost()).isEqualToIgnoringHours(LocalDateTime.of(2015,9,14,0,0))
        );
    }

    @Test
    @DisplayName("Should return bad request when url is empty")
    void shouldReturnBadRequestWhenUrlEmptyString() {
        //arrange
        var analyseParams = new AnalyseParams(Strings.EMPTY);

        //act && assert
        webClient.post().uri("/analyze")
                .bodyValue(analyseParams)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return bad request when url is null")
    void shouldReturnBadRequestWhenUrlNull() {
        //arrange
        var analyseParams = new AnalyseParams(null);

        //act && assert
        webClient.post().uri("/analyze")
                .bodyValue(analyseParams)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should return bad request when url is incorrect")
    void shouldReturnBadRequestWhenGivenBadUrl() {
        //arrange
        var analyseParams = new AnalyseParams(BAD_URL);
        MockWebClientResponse.getWebClientMock(mockWebClient, new ByteArrayResource(new byte[0]));

        //act && assert
        webClient.post().uri("/analyze")
                .bodyValue(analyseParams)
                .exchange()
                .expectStatus().isBadRequest();
    }

}