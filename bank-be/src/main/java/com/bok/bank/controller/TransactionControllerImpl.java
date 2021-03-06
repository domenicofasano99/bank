package com.bok.bank.controller;

import com.bok.bank.helper.TransactionHelper;
import com.bok.bank.integration.dto.AuthorizationRequestDTO;
import com.bok.bank.integration.dto.AuthorizationResponseDTO;
import com.bok.bank.integration.dto.TransactionDTO;
import com.bok.bank.integration.dto.TransactionResponseDTO;
import com.bok.bank.integration.dto.WireTransferRequestDTO;
import com.bok.bank.integration.dto.WireTransferResponseDTO;
import com.bok.bank.integration.service.TransactionController;
import com.bok.bank.model.Transaction;
import com.bok.bank.util.Money;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.bok.bank.util.Constants.UNKNOWN_MARKET;

@Service
@Slf4j
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    TransactionHelper transactionHelper;

    @Override
    public List<TransactionResponseDTO> allTransaction(Long accountId) {
        return transactionHelper.findTransactionsByAccountId(accountId);
    }

    @Override
    public AuthorizationResponseDTO authorize(Long accountId, AuthorizationRequestDTO request) {
        log.info("Authorize passed param accountId: {} authorizationRequest: {}", accountId, request);
        Preconditions.checkNotNull(accountId, "accountId is null");
        Preconditions.checkNotNull(request.money, "Money passed is null");
        Preconditions.checkNotNull(request.money.amount, "Amount passed is null");
        Preconditions.checkArgument(request.money.amount.intValue() > 0, "Amount passed is ZERO or negative");
        Preconditions.checkNotNull(request.money.currency, "Currency passed is null");
        request.fromMarket = StringUtils.isBlank(request.fromMarket) ? UNKNOWN_MARKET : request.fromMarket;
        return transactionHelper.authorizeTransaction(accountId, Money.money(request.money.amount, request.money.currency), request.fromMarket, request.cardToken);
    }

    @Override
    public WireTransferResponseDTO wireTransfer(Long accountId, WireTransferRequestDTO wireTransferRequestDTO) {
        log.info("param passed to wire transfer are: {}, {}", accountId, wireTransferRequestDTO.toString());
        Preconditions.checkNotNull(wireTransferRequestDTO.money, "money is null");
        Preconditions.checkNotNull(wireTransferRequestDTO.causal, "causal is null");
        Preconditions.checkNotNull(wireTransferRequestDTO.money.amount, "money is null");
        Preconditions.checkArgument(StringUtils.isNotBlank(wireTransferRequestDTO.destinationIBAN), "destinationIBAN passed is blank");
        wireTransferRequestDTO.destinationIBAN = wireTransferRequestDTO.destinationIBAN.replace(" ", "").toUpperCase(Locale.ROOT);
        try {
            Iban.valueOf(wireTransferRequestDTO.destinationIBAN);
        } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
            return new WireTransferResponseDTO(false, e.getLocalizedMessage());

        }
        if (wireTransferRequestDTO.instantTransfer) {
            boolean success = transactionHelper.performTransaction(new TransactionDTO(wireTransferRequestDTO.money, accountId, wireTransferRequestDTO.destinationIBAN, wireTransferRequestDTO.causal, wireTransferRequestDTO.beneficiary, wireTransferRequestDTO.instantTransfer, wireTransferRequestDTO.executionDate, Transaction.Type.WIRE_TRANSFER.name()));
            if(success){
                return new WireTransferResponseDTO(true, "");
            }
            return new WireTransferResponseDTO(false, "Amount not available");

        }
        Preconditions.checkNotNull(wireTransferRequestDTO.executionDate, "executionDate passed is null");
        Preconditions.checkArgument(wireTransferRequestDTO.executionDate.isAfter(LocalDate.now()), "executionDate is before then " + LocalDate.now());
        return transactionHelper.authorizeWireTransfer(new TransactionDTO(wireTransferRequestDTO.money, accountId, wireTransferRequestDTO.destinationIBAN, wireTransferRequestDTO.causal, wireTransferRequestDTO.beneficiary, wireTransferRequestDTO.instantTransfer, wireTransferRequestDTO.executionDate, Transaction.Type.WIRE_TRANSFER.name()));
    }


    @Override
    public List<TransactionResponseDTO> getCardTransaction(Long accountId, String token) {
        log.info("get card transaction account id: {}, token: {}", accountId, token);

        List<Transaction> transactions = transactionHelper.findTransactionByCardToken(accountId, token);
        return transactions.stream().map(t -> transactionHelper.toTransactionResponseDTO(t, accountId)).collect(Collectors.toList());
    }

}
