package com.makersacademy.find_a_pill.service;

import com.makersacademy.find_a_pill.model.Pill;
import com.makersacademy.find_a_pill.repository.PillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PillServiceImpl implements PillService {


  private final PillRepository repository;

  @Autowired
  public PillServiceImpl(PillRepository pillRepository) {
    this.repository = pillRepository;
  }

  @Override
  public Pill save(Pill pill) {
    return repository.save(pill);
  }
}
