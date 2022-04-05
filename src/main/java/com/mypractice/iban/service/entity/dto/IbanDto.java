package com.mypractice.iban.service.entity.dto;

import com.mypractice.iban.service.constants.MessageConstant;
import lombok.Data;
import com.mypractice.iban.service.constraints.CountryConstraint;

import javax.validation.constraints.NotEmpty;

@Data
public class IbanDto {

    @NotEmpty(message = MessageConstant.COUNTRY_CODE_INVALID)
    @CountryConstraint
    private String countryCode;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
}
