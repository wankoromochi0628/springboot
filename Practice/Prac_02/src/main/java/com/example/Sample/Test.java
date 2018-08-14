package com.example.Sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {
    @Autowired
    TestInterface testInterface;

    Customer customer;

// ***********************************************
//	@GetMapping アノテーション
//	「/」というパスでアクセスがあった場合、「homeメソッド」
//	が呼び出される。
// ***********************************************

    // GET = SELECT文
    @RequestMapping(value="/page1", method={RequestMethod.GET})
    Customer home() {
        customer = testInterface.findOne(1);
        return customer;
    }

    // POST = INSERT文
    @RequestMapping(value="/page2", method={RequestMethod.POST})
    List<Customer> test1() {
        List<Customer> customerList = testInterface.findAll();
        return customerList;
    }

    // PUT = UPDATE文
    @RequestMapping(value="/page3", method={RequestMethod.PUT})
    String test3() {
        customerRepository
        return customer;
    }

    // DELETE = DELETE文
    @RequestMapping(value="/page4", method={RequestMethod.DELETE})
    String test4() {
        return "test4";
    }
}
