package com.schwarz.libraryapp.repository;


import com.schwarz.libraryapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByIdIn(List<Long> userIds);

    Optional<Customer> findByName(String name);
    Boolean existsByEmail(String email);
    @Override
    boolean existsById(Long id);
}
