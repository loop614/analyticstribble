package com.loop614.analyticstribble.tracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "tribble-tracker")
public class Tracker {

    private @Id
    String id;

    private @Field(type = Keyword)
    String swipeId;

    private @Field(type = Integer)
	Integer x;

	private @Field(type = Integer)
    Integer y;

	private @Field(type = Long)
	Long dt;
}
