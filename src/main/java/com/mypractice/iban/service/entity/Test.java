package com.mypractice.iban.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mypractice.iban.service.enums.CountryCode;
import com.mypractice.iban.service.entity.iban.Iban;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.SA)
                .bankCode("80")
                .branchCode("")
                .accountNumber("000000608010167519").build();

        System.out.println(iban.getIbanValue());
        System.out.println(iban.getIbanValue().length());
//        IbanDto ibanDto =  new IbanDto();
//        ibanDto.setAccountNumber("00123377");
//        ibanDto.setBankCode("BUPB");
//        ibanDto.setBranchCode("123456");
//        ibanDto.setCountryCode(CountryCode.SA);
//        System.out.println(iban.getIbanValue());
     //   System.out.println(Iban.valueOf("SA42BUKB20201655555556").getIbanValue());
     //  String result = new ObjectMapper().writeValueAsString(ibanDto);
       // System.out.println(result);
       // System.out.println(Iban.valueOf("SA55BUKB12345600223377").getIbanValue());
    }
}
