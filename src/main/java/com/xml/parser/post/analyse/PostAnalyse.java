package com.xml.parser.post.analyse;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostAnalyse {

    private final LocalDateTime analyseDate;
    private final PostAnalyseDetails details;
}
