package com.example.Sample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestInterface extends JpaRepository<Customer, Integer> {
}
