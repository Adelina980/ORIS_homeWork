package org.example.service;


import org.example.model.Profession;
import org.example.repository.ProfessionRepository;

import java.util.ArrayList;
import java.util.List;

public class ProfessionService {

    private ProfessionRepository repository;

    public ProfessionService() {
        repository = new ProfessionRepository();
    }

    public List<Profession> findAll(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    public List<Profession> findByName(String name, int limit, int offset) {
        return repository.findByName(name, limit, offset);
    }

    public Integer countResults(String name) {
        return repository.countResults(name);
    }
}
