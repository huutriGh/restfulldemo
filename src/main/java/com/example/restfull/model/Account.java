package com.example.restfull.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Account")
public class Account {


    @Id
    @Column(name = "AccountNo",columnDefinition = "varchar(20)")
    private String accountNo;

    @Column(name = "AccountName", columnDefinition = "varchar(50)")
    private  String accountName;

    @Column(name = "PinCode", columnDefinition = "varchar(50)")
    private  String pinCode;

    @Column(name = "Balance", columnDefinition = "decimal(15,2)")
    private  double balance;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "account")
    private Set<SaveTransaction> saveTransactionSet = new HashSet<>() ;


}
