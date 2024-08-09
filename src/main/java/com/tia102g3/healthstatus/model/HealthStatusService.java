package com.tia102g3.healthstatus.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("healthStatusService")
public class HealthStatusService {

    @Autowired
    HealthStatusRepository repository;

    public void deleteByHealthStatusSN(Integer healthSN) {
        repository.deleteById(healthSN);
    }

    public List<HealthStatusVO> getAll() {
        return repository.findAll();
    }

    public void addHealthStatus(HealthStatusVO healthStatusVO) {
        repository.save(healthStatusVO);
    }

    public void updateHealthStatus(HealthStatusVO healthStatusVO) {
        repository.save(healthStatusVO);
    }
    
    
    public HealthStatusVO getOneHealthStatus(Integer healthSN) {
        Optional<HealthStatusVO> optional = repository.findById(healthSN);

        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}
