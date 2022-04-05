package com.mypractice.iban.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mypractice.iban.service.entity.modal.Countries;

import java.util.Optional;

@Repository
public interface CountriesRepo extends JpaRepository<Countries, Integer> {
    Optional<Countries> findByCountry2AlphaCodeOrCountry3AlphaCode(String country2AlphaCode, String country3AlphaCode);
}
