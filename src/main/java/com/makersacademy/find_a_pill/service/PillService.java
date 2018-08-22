package com.makersacademy.find_a_pill.service;

import com.makersacademy.find_a_pill.model.Pill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PillService {

  Pill save(Pill pill);

  Page<Pill> findByTitle(String string, Pageable pageable);

  Page<Pill> findByTitleWithCustomQuery(String string, Pageable pageable);
}
