package com.makersacademy.find_a_pill.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "pharmacy", type = "pill")
@Data
public class Pill {

  @Id
  private String id;

  private String title;

  public Pill(String title) {
    this.title = title;
  }

}
