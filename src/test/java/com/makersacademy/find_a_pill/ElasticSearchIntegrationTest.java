package com.makersacademy.find_a_pill;

import static org.junit.Assert.assertEquals;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
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

    Pill pill;

    pill = new Pill("bdd");
    service.save(pill);

    pill = new Pill("blocks", "www.blocks.com", "blocks of bdd");
    service.save(pill);

    pill = new Pill("levelling up");
    String[] tags = new String[]{"level", "up", "bdd"};
    pill.setTags(tags);
    service.save(pill);

    pill = new Pill("mvc");
    service.save(pill);
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

  @Test
  public void givenPillExists_whenFindByTitle_thenPillTitleIsReturned() {
    Page<Pill> response = service.findByTitle("bdd", PageRequest.of(0, 10));

    assertEquals(1L, response.getTotalElements());
  }

  @Test
  public void givenPillsExist_whenFindByTitle_thenReturnsMultiple() {
    Pill pill = new Pill("bdd");
    service.save(pill);
    Page<Pill> response = service.findByTitle("bdd", PageRequest.of(0, 10));

    assertEquals(2L, response.getTotalElements());
  }

  @Test
  public void givenNoPillExists_whenFindByTitle_thenReturnsNoResults() {
    Page<Pill> response = service.findByTitle("b", PageRequest.of(0, 10));

    assertEquals(0L, response.getTotalElements());
  }

  @Test
  public void givenPillsExist_whenFindByTitleWithCustomQuery_thenReturnsMutlipleResults() {
    Page<Pill> response = service.findByTitleWithCustomQuery("b", PageRequest.of(0, 10));

    assertEquals(2L, response.getTotalElements());
  }

  @Test
  public void whenFindAll_thenReturnsAllResults() {
    Page<Pill> response = service.findAll(PageRequest.of(0, 10));

    assertEquals(4L, response.getTotalElements());
  }

  @Test
  public void givenPillWithURL_ReturnsTitleAndURLAndSummary() {
    final String summary = "After this, there is no turning back. You take the blue pill—the story "
        + "ends, you wake up in your bed and believe whatever you want to believe. You take the red"
        + " pill—you stay in Wonderland, and I show you how deep the rabbit hole goes. Remember: al"
        + "l I'm offering is the truth.";

    Pill pill = new Pill("magic pills", "www.magicpills.com", summary);
    service.save(pill);
    Page<Pill> response  = service.findByTitleWithCustomQuery("magic", PageRequest.of(0, 10));

    assertEquals("www.magicpills.com", response.getContent().get(0).getUrl());
    assertEquals("magic pills", response.getContent().get(0).getTitle());
    assertEquals(summary, response.getContent().get(0).getSummary());
  }

  @Test
  public void givenMatchingSearchCriteria_whenFindByTitleAndTagAndSummaryWithCustomQuery_ReturnsAllMatches() {
    Page<Pill> response = service.findByTitleAndTagAndSummaryWithCustomQuery("bdd", PageRequest.of(0, 10));

    assertEquals(3L, response.getTotalElements());
  }

}
