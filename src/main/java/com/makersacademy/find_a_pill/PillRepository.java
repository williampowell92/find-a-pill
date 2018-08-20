package com.makersacademy.find_a_pill;

import java.awt.print.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PillRepository extends ElasticsearchRepository<Pill, Long> {

  Page<Pill> findByTitle(String title, Pageable pageable)

}
