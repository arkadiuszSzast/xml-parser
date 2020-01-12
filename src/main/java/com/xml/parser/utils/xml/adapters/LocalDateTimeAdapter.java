package com.xml.parser.utils.xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String s) {
        return LocalDateTime.parse(s);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }
}
