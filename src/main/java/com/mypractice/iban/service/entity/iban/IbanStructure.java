package com.mypractice.iban.service.entity.iban;


import com.mypractice.iban.service.enums.CountryCode;

import java.util.EnumMap;
import java.util.List;


public class IbanStructure {

    private final List<IbanStructureEntry> entries;

    public IbanStructure(List<IbanStructureEntry> entries) {
        this.entries = entries;
    }

    private static final EnumMap<CountryCode, IbanStructure> structures;

    static {
        structures = new EnumMap<>(CountryCode.class);
        structures.put(CountryCode.SA, new IbanStructure(
                List.of(IbanStructureEntry.bankCode(2, 'N'),
                        IbanStructureEntry.accountNumber(18, 'C'))));

        structures.put(CountryCode.GB, new IbanStructure(
                List.of(IbanStructureEntry.bankCode(4, 'A'),
                        IbanStructureEntry.branchCode(6, 'N'),
                        IbanStructureEntry.accountNumber(8, 'N'))));
    }

    public static IbanStructure forCountry(final CountryCode countryCode) {
        return structures.get(countryCode);
    }


    public List<IbanStructureEntry> getEntries() {
        return entries;
    }

    public int getIbnLength() {
        return entries.stream()
                .map(IbanStructureEntry::getLength)
                .reduce(0, Integer::sum);
    }
}
