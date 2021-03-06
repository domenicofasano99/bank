package com.bok.bank;

import com.bok.bank.integration.dto.BankAccountInfoDTO;
import com.bok.bank.integration.service.BankAccountController;
import com.bok.bank.model.BankAccount;
import com.bok.bank.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BankAccountControllerTest {

    @Autowired
    BankAccountController bankAccountController;

    @Autowired
    ModelTestUtil modelTestUtil;

    @Before
    public void init() {
        modelTestUtil.clearAll();
        modelTestUtil.populateDB();
    }

    @Test
    public void bankAccountInfoTest() {
        User user = modelTestUtil.createAndSaveUser(17L);
        log.info(user.toString());
        BankAccount bankAccount = modelTestUtil.createAndSaveBankAccount(user);
        BankAccountInfoDTO bankAccountInfoDTO = bankAccountController.bankAccountInfo(user.getId());
        assertEquals(bankAccountInfoDTO.bankAccountName, bankAccount.getName());
        assertEquals(bankAccountInfoDTO.email, user.getEmail());
        assertEquals(bankAccountInfoDTO.IBAN, bankAccount.getIBAN());
        assertEquals(bankAccountInfoDTO.label, bankAccount.getLabel());
        assertEquals(bankAccountInfoDTO.currency, bankAccount.getCurrency());
        assertEquals(bankAccountInfoDTO.blockedAmount.setScale(6), bankAccount.getBlockedAmount().getValue().setScale(6));
        assertEquals(bankAccountInfoDTO.availableAmount.setScale(6), bankAccount.getAvailableAmount().getValue().setScale(6));
        assertEquals(bankAccountInfoDTO.status, bankAccount.getStatus().name());
    }


}
