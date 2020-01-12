package com.xml.parser.utils;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

public class MockWebClientResponse {

    public static void getWebClientMock(WebClient mockWebClient, ByteArrayResource resp) {
        final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        final var acceptSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        final var retrieveSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

        when(mockWebClient.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(acceptSpecMock);
        when(acceptSpecMock.accept(MediaType.APPLICATION_XML)).thenReturn(retrieveSpecMock);
        when(retrieveSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<ByteArrayResource>>notNull()))
                .thenReturn(Mono.just(resp));

    }
}
