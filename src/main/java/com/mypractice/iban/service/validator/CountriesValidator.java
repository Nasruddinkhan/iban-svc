package com.mypractice.iban.service.validator;

import com.mypractice.iban.service.entity.modal.Countries;
import com.mypractice.iban.service.repository.CountriesRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mypractice.iban.service.constraints.CountryConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CountriesValidator implements ConstraintValidator<CountryConstraint, String> {

    @Autowired
    private final CountriesRepo countriesRepo;

    @Override
    public void initialize(CountryConstraint countryCode) {
        System.out.println("CountriesValidator.initialize countryCode [" + countryCode + "]");
    }

    @Override
    public boolean isValid(String countryCode,
                           ConstraintValidatorContext cxt) {
        System.out.println("countryCode" + countryCode);
        Optional<Countries> countries = countriesRepo.findByCountry2AlphaCodeOrCountry3AlphaCode(countryCode, countryCode);
        return countries.isPresent();

    }
}
