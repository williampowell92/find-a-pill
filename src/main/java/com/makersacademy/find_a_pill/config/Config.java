package com.makersacademy.find_a_pill.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.makersacademy.find_a_pill")
public class Config {

  @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch@5.6/5.6.10}")
  private String elasticsearchHome;

  @Value("${elasticsearch.cluster.name:elasticsearch_williampowell}")
  private String clusterName;

  @Bean
  public Client client() throws Exception {

    Settings settings = Settings.builder()
        .put("path.home", elasticsearchHome)
        .put("cluster.name", clusterName)
        .build();

    Client client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

    return client;
  }


  @Bean
  public ElasticsearchOperations elasticsearchTemplate() throws Exception {
    return new ElasticsearchTemplate(client());
  }

}
