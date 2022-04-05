package com.mypractice.iban.service.boundary;


import com.mypractice.iban.service.constants.MessageConstant;
import com.mypractice.iban.service.entity.dto.IbanDto;
import com.mypractice.iban.service.enums.CountryCode;
import com.mypractice.iban.service.util.MessageResourceUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mypractice.iban.config.exception.IbanException;
import com.mypractice.iban.service.entity.dto.ErrorDetails;
import com.mypractice.iban.service.entity.iban.Iban;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/iban")
@AllArgsConstructor
public class IbanResources {
    private final MessageResourceUtil messageResourceUtil;
    @PostMapping
    public ResponseEntity<Iban> createIban(@RequestBody @Valid IbanDto ibanDto) {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.getByCode(ibanDto.getCountryCode()))
                .bankCode(ibanDto.getBankCode())
                .branchCode(ibanDto.getBranchCode())
                .accountNumber(ibanDto.getAccountNumber()).build();
        return new ResponseEntity<>(iban, HttpStatus.CREATED);
    }

    @GetMapping("/{iban}/validate")
    public ResponseEntity<ErrorDetails> validateIban(@PathVariable String iban) {
        Iban validIbn = Iban.valueOf(iban);
        ErrorDetails errorDetails = Optional.ofNullable(validIbn).map(e -> ErrorDetails.builder().errorCode(MessageConstant.SUCCESS_CODE)
                .errorMessage(messageResourceUtil.getMessage(MessageConstant.VALID_IBAN_MSG)).build()).orElseThrow(() -> new IbanException(MessageConstant.INVALID_IBAN_MSG));
        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
    }
}
