package com.mypractice.iban.service.entity.iban;


import com.mypractice.iban.service.enums.CountryCode;
import com.mypractice.iban.service.enums.IbanEntryType;
import com.mypractice.iban.service.util.CommonUtil;
import com.mypractice.iban.config.exception.IbanException;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public final class Iban {

    public static final String DEFAULT_CHECK_DIGIT = "00";
    private final String value;

    private Iban(final String value) {
        this.value = value;
    }


    public String getIbanValue() {
        return value;
    }

    public static Iban valueOf(final String iban) {
        CommonUtil.validate(iban);
        return new Iban(iban);
    }

    public static final class Builder {

        private CountryCode countryCode;
        private String bankCode;
        private String branchCode;
        private String accountNumber;


        public Builder countryCode(final CountryCode countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder bankCode(final String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder branchCode(final String branchCode) {
            this.branchCode = branchCode;
            return this;
        }

        public Builder accountNumber(final String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Iban build() {
            return build(true);
        }

        public Iban build(boolean validate) {
            if (countryCode == null) throw new IbanException("Country Code cannot be null");
            if (bankCode == null) throw new IbanException("bank Code cannot be null");
            if (branchCode == null) throw new IbanException("branch Code cannot be null");
            if (accountNumber == null) throw new IbanException("account number cannot be null");

            return Optional.ofNullable(formatIbanSupplier.get())
                    .map(CommonUtil::calculateCheckDigit)
                    .map(v -> CommonUtil.replaceCheckDigit(formatIbanSupplier.get(), v))
                    .map(v -> {
                        if (validate) CommonUtil.validate(v);
                        return new Iban(v);
                    }).orElseThrow(() -> new RuntimeException("Default value cannot not e null or empty"));

        }


        Supplier<String> formatIbanSupplier = () -> countryCode.getByAlpha2() +
                DEFAULT_CHECK_DIGIT +
                formatBan();


        private String formatBan() {
            final IbanStructure structure = IbanStructure.forCountry(countryCode);
            return Optional.ofNullable(structure).map(struct -> struct.getEntries().stream()
                    .map(this::getValues).collect(Collectors.joining())).orElseThrow(() -> new RuntimeException("country code is not support"));
        }

        public String getValues(IbanStructureEntry ibanEntryType) {
            String result = "";
            if (ibanEntryType.getIbanEntryType().equals(IbanEntryType.BANK_CODE))
                result = bankCode;
            else if (ibanEntryType.getIbanEntryType().equals(IbanEntryType.BRANCH_CODE))
                result = branchCode;
            else if (ibanEntryType.getIbanEntryType().equals(IbanEntryType.ACCOUNT_NUMBER))
                result = accountNumber;
            return result;
        }
    }


}
