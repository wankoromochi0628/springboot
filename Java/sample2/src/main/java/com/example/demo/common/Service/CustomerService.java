package com.example.demo.common.Service;

import com.example.demo.common.Domain.Customer;
import com.example.demo.common.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Service
 * 「@Component」と基本的には同じ働きを持つ。
 * ビジネスロジックなどのService層を対象としている。
 *
 * @Transactional
 * トランザクション境界を設定する。
 * 対象のメソッドで発行したクエリのトランザクション処理を行う。
 * */
 @Service
 @Transactional
public class CustomerService {
     @Autowired
     CustomerRepository customerRepository;

     public List<Customer> findAll() { return customerRepository.findAllOrderByName(); }

     public Page<Customer> findAll(Pageable pageable) { return customerRepository.findAllOrderByName(pageable); }

     public Customer findOne(Integer id) { return customerRepository.findOne(id); }

     public Customer create(Customer customer) { return customerRepository.save(customer); }

     public Customer update(Customer customer) { return customerRepository.save(customer); }

     public void delete(Integer id) { customerRepository.delete(id); }

//     public void deleteAll() { customerRepository.deleteAll(); }
}
