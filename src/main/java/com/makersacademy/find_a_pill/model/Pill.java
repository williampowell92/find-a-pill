package com.makersacademy.find_a_pill.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "pharmacy", type = "pill")
@Data
public class Pill {

  @Id
  private String id;

  private String title;

  private String Url;

  public Pill() {}

  public Pill(String title) {
    this.title = title;
  }

  public Pill(String title, String Url) {
    this.title = title;
    this.Url = Url;
  }

}
