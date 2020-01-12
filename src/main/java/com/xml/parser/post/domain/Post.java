package com.xml.parser.post.domain;

import com.xml.parser.utils.xml.adapters.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement ( name = "row" )
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @XmlAttribute(name = "Id")
    private Long id;
    @XmlAttribute(name = "CreationDate")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime creationDate;
    @XmlAttribute(name = "Score")
    private Long score;
    @XmlAttribute(name = "ParentId")
    private Long parentId;
    @XmlAttribute(name = "AcceptedAnswerId")
    private Long acceptedAnswerId;

}

