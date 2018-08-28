package com.example.demo.common.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Entity
 * JPAのエンティティであることを示す
 *
 * @Table(name = "customers")
 * エンティティに対応するテーブル名を指定
 * デフォルトでは「テーブル名=クラス名」  ＊(name="任意のテーブル名")にも指定できる
 */
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

/*
 * @Id
 * 主キーであることを示す
 */
    @Id
    @GeneratedValue
    private Integer id;

/*
 * @Column
 * カラムであることを示す
*/
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public Customer(String test1, String test2){
    }
}
