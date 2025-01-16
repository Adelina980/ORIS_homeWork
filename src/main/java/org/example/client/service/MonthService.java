package org.example.client.service;

import org.example.model.Month;
import org.example.client.restClient.IMonthDataSource;
import org.example.client.restClient.MonthRestDataSource;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MonthService {

    private IMonthDataSource dataSource = new MonthRestDataSource();

    public List<Month> findAll() throws URISyntaxException, IOException, InterruptedException {
        return dataSource.findAll();
    }

    public Month findById(Long id) throws URISyntaxException, IOException, InterruptedException {
        return dataSource.findById(id);
    }

    public Month save(Month month) throws URISyntaxException, IOException, InterruptedException{
        return dataSource.save(month);
    }

    public void delete(Long id) throws URISyntaxException, IOException, InterruptedException{
        dataSource.delete(id);
    }
}
