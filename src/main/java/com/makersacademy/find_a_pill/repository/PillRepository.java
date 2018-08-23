package com.makersacademy.find_a_pill.repository;

import com.makersacademy.find_a_pill.model.Pill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillRepository extends ElasticsearchRepository<Pill, String> {
  Page<Pill> findAll(Pageable pageable);

  Page<Pill> findByTitle(String title, Pageable pageable);

  @Query("{\"regexp\": { \"title\": \".*?0.*\" } }")
  Page<Pill> findByTitleWithCustomQuery(String title, Pageable pageable);

  @Query("{ \"bool\": { \"should\": [ { \"regexp\": { \"title\": \".*?0.*\" } }, { \"match\": { \"summary\": \"?0\" } }, { \"match\": { \"tags\": \"?0\" } } ] } }")
  Page<Pill> findByTitleAndTagAndSummaryWithCustomQuery(String string, Pageable pageable);
}
