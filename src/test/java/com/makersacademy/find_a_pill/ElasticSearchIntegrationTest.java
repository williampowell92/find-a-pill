package com.makersacademy.find_a_pill;

import static org.junit.Assert.assertNotNull;

import com.makersacademy.find_a_pill.config.Config;
import com.makersacademy.find_a_pill.model.Pill;
import com.makersacademy.find_a_pill.service.PillService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = Config.class)
public class ElasticSearchIntegrationTest {

  @Autowired
  private ElasticsearchTemplate template;

  @Autowired
  private PillService service;

  @Before
  public void setUp() {
    template.createIndex(Pill.class);
  }

  @After
  public void tearDown() {
    template.deleteIndex(Pill.class);
  }

  @Test
  public void givenPillService_whenSaveArticle_thenIdIsAssigned() {
    Pill pill = new Pill("Spring101");
    pill = service.save(pill);
    assertNotNull(pill.getId());
  }

}
