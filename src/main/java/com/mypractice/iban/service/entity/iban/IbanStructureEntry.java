package com.mypractice.iban.service.entity.iban;

import com.mypractice.iban.service.enums.IbanEntryType;
import com.mypractice.iban.service.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.mypractice.iban.service.enums.EntryCharacterType;

import java.util.EnumMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class IbanStructureEntry {
    private final IbanEntryType ibanEntryType;
    private final EntryCharacterType characterType;
    private final int length;

    private static Map<EntryCharacterType, char[]> entryCharacterTypeMap;

    static {
        entryCharacterTypeMap = new EnumMap<>(EntryCharacterType.class);
        entryCharacterTypeMap.put(EntryCharacterType.N, CommonUtil.getDigits().toCharArray());
        entryCharacterTypeMap.put(EntryCharacterType.A, CommonUtil.getCharacter().toCharArray());
        entryCharacterTypeMap.put(EntryCharacterType.C, (CommonUtil.getDigits().concat(CommonUtil.getCharacter())).toCharArray());
    }


    public static IbanStructureEntry bankCode(final int length, final char charType) {
        return new IbanStructureEntry(IbanEntryType.BANK_CODE, EntryCharacterType.valueOf(String.valueOf(charType)), length);
    }

    public static IbanStructureEntry accountNumber(final int length, final char charType) {
        return new IbanStructureEntry(IbanEntryType.ACCOUNT_NUMBER, EntryCharacterType.valueOf(String.valueOf(charType)), length);
    }

    public static IbanStructureEntry branchCode(int length, final char charType) {
        return new IbanStructureEntry(IbanEntryType.BRANCH_CODE, EntryCharacterType.valueOf(String.valueOf(charType)), length);
    }
}
