package com.example.restfull.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private String accountSource;

    private double amount;

    private String comment;

    private String accountTarget;
}
