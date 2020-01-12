package com.xml.parser.post.analyse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xml.parser.utils.validator.AnalyseParamsConstraint;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = AnalyseParams.AnalyseParamsBuilder.class)
@Builder
@Value
@AnalyseParamsConstraint
public class AnalyseParams {

    @JsonProperty("url")
    private final String url;
}

