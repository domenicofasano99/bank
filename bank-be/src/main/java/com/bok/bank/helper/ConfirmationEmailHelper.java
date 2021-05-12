package com.bok.bank.helper;

import com.bok.bank.model.Account;
import com.bok.bank.model.ConfirmationEmailHistory;
import com.bok.bank.repository.ConfirmationEmailHistoryRepository;
import com.bok.bank.util.Generator;
import com.bok.bank.util.exception.ConfirmationTokenException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationEmailHelper {


    @Autowired
    ConfirmationEmailHistoryRepository confirmationEmailHistoryRepository;

    @Autowired
    Generator generator;

    public ConfirmationEmailHistory findAndVerifyConfirmationToken(Long accountId, String confirmationToken, ConfirmationEmailHistory.ResourceType resourceType){
        ConfirmationEmailHistory confirmationEmailHistory = confirmationEmailHistoryRepository.findByConfirmationTokenAndResourceType(confirmationToken, resourceType).orElseThrow(ConfirmationTokenException::new);
        Preconditions.checkArgument(confirmationEmailHistory.getAccount().getId().equals(accountId), "Operation unauthorized from your account");
        return confirmationEmailHistory;
    }

    public ConfirmationEmailHistory saveConfirmationEmail(Account account, Long resourceId, ConfirmationEmailHistory.ResourceType resourceType){
        ConfirmationEmailHistory confirmationEmailHistory = new ConfirmationEmailHistory(account, resourceId, resourceType, generator.generateConfirmationToken());
        return confirmationEmailHistoryRepository.save(confirmationEmailHistory);
    }

}