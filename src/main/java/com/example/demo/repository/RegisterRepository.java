package com.example.demo.repository;

import javax.transaction.Transactional;

import com.example.demo.model.Register;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface RegisterRepository extends CrudRepository<Register,Integer>{
        Register findByName(String name);
}