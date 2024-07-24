package com.loop614.analyticstribble.tracker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "tribble-tracker")
public class Tracker {

	private @Id String id;
	private @Field(type = Keyword) String domain;
    private @Field(type = Keyword) String customer;
	private @Field(type = Date) LocalDate date;
	private BigDecimal dt;
    private BigDecimal x;
	private BigDecimal y;
}
