package com.example.restfull.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "SaveTransaction")
public class SaveTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", columnDefinition = "int")
    private int id;

    @Column(name = "TranDate", columnDefinition = "Date")
    private Date trandate;

    @Column(name = "Amount", columnDefinition = "decimal(15,2)")
    private double amount;

    @Column(name = "Comment", columnDefinition = "varchar(250)")
    private String comment;

    @Column(name = "AccountNo", columnDefinition = "varchar(20)")
    private String accountNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountNo", insertable = false, updatable = false)
    private Account account;
}
