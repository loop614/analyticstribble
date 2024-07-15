package com.loop614.analyticstribble.tracker.entity;

import java.math.BigDecimal;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Builder
@Document(indexName = "tribble-tracker")
public class Tracker {

	private @Id String id;
	private String domain;
    private String user;
	private @Field(type = Date) String date;
    private BigDecimal x;
	private BigDecimal y;
}
