package com.book.shop.mapper;

import com.book.shop.dto.AccountResponse;
import com.book.shop.model.Accounts;

import java.util.ArrayList;
import java.util.List;

public class AccountConverter {

    public static List<AccountResponse> convertToResponseList(List<Accounts> payload){
        List<AccountResponse> responseList = new ArrayList<>();
        for(Accounts acct: payload){
            AccountResponse response = new AccountResponse();
            response.setAccountType(acct.getAccountType().toString());
            response.setEmail(acct.getEmail());
            response.setPhone(acct.getPhone());
            response.setLastName(acct.getLastName());
            response.setFirstName(acct.getFirstName());
            response.setDateCreated(acct.getDateCreated());
            response.setAppUserName(acct.getAppUserName());
            responseList.add(response);
        }
        return responseList;
    }
}
