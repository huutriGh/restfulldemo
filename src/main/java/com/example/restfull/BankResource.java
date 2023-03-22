package com.example.restfull;

import com.example.restfull.dto.LoginRequest;
import com.example.restfull.dto.LoginResponse;
import com.example.restfull.dto.TransferReponse;
import com.example.restfull.dto.TransferRequest;
import com.example.restfull.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;


@Path("/account")
public class BankResource {
    @Inject
    AccountService accountService;
    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public LoginResponse login(LoginRequest loginRequest) {
       return  accountService.login(loginRequest);
    }

    @POST
    @Path("/transfer")
    @Produces("application/json")
    @Consumes("application/json")
    public TransferReponse tranfer(TransferRequest transferRequest ) {
        return  accountService.transfer(transferRequest);
    }
}