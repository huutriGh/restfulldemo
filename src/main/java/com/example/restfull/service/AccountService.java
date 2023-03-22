package com.example.restfull.service;

import com.example.restfull.dto.LoginRequest;
import com.example.restfull.dto.LoginResponse;
import com.example.restfull.dto.TransferReponse;
import com.example.restfull.dto.TransferRequest;
import com.example.restfull.model.SaveTransaction;

public interface AccountService {

    LoginResponse login(LoginRequest loginRequest);
    TransferReponse transfer(TransferRequest saveTransaction);

}
