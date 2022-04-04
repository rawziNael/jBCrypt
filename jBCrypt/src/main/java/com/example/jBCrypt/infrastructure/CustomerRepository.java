package com.example.jBCrypt.infrastructure;

import com.example.jBCrypt.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findAllByUsername(String username);
}
