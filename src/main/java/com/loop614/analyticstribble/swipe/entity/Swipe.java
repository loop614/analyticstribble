package com.loop614.analyticstribble.swipe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date_Nanos;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "tribble-swipe")
public class Swipe {

    private @Id
    String id;

    private @Field(type = Keyword)
    String domain;

    private @Field(type = Keyword)
    String customer;

    private @Field(type = Date_Nanos)
    String datenano;
}
