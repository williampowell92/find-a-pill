package com.makersacademy.find_a_pill.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "pharmacy", type = "pill")
@Data
public class Pill {

  @Id
  private Long id;

  private String title;

}
