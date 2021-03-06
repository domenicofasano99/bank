package com.bok.bank.util;

import com.bok.bank.model.ConfirmationEmailHistory;
import com.bok.bank.repository.BankAccountRepository;
import com.bok.bank.repository.ConfirmationEmailHistoryRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class Generator {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    ConfirmationEmailHistoryRepository confirmationEmailHistoryRepository;

    @Value("${bank-info.bank-code}")
    private String BANK_CODE;

    public String generateIBAN() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.IT)
                .bankCode(BANK_CODE)
                .buildRandom();
        return bankAccountRepository.existsByIBAN(iban.toFormattedString()) ? generateIBAN() : iban.toString();
    }

    public String generateConfirmationToken() {
        String confirmationToken = UUID.randomUUID().toString();
        return confirmationEmailHistoryRepository.existsByConfirmationToken(confirmationToken) ? generateConfirmationToken() : confirmationToken;
    }

    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character. String.format("%06d", number)
        // recursive until not produce unique code
        return confirmationEmailHistoryRepository.existsByConfirmationToken(String.format("%06d", number)) ? getRandomNumberString() : String.format("%06d", number);
    }

    public String generateUrlServiceByResourceType(ConfirmationEmailHistory.ResourceType resourceType) {
        switch (resourceType) {
            case CARD:
                return "/card";
            case TRANSACTION:
                return "/transaction";
            case BANK_ACCOUNT:
                return "/bankAccount";
            default:
                throw new IllegalStateException("ResourceType Not found");
        }
    }
}
