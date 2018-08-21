package com.makersacademy.find_a_pill;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PillRepository extends ElasticsearchRepository<Pill, Long> {

  Page<Pill> findByTitle(String title, Pageable pageable);

}
