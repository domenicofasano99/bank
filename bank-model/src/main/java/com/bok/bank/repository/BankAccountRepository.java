package com.bok.bank.repository;

import com.bok.bank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Currency;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountId(Long accountId);

    boolean existsByIBAN(String IBAN);

    @Query("UPDATE BankAccount set status = :status where id= :bankAccountId")
    int changeBankAccountStatus(Long bankAccountId, BankAccount.Status status);

    @Query("SELECT count(ba) > 0 FROM BankAccount ba where ba.accountId = :accountId and ba.status NOT LIKE 'DELETED'")
    boolean existsBankAccountNotDeletedByAccountId(Long accountId);

    Optional<BankAccount> findByAccountIdAndStatus(Long accountId, BankAccount.Status status);

    @Query("SELECT ba.id as id, ba.name as bankAccountName, ba.currency as currency, ba.status as status FROM BankAccount ba WHERE ba.accountId = :accountId")
    Optional<Projections.BankAccountBasicInfo> findBankAccountBasicInfoByAccountId(Long accountId);

    Optional<BankAccount> findBankAccountByIBAN(String IBAN);

    public class Projections {
        public interface BankAccountBasicInfo {
            Long getId();

            String getBankAccountName();

            Currency getCurrency();

            BankAccount.Status getStatus();
        }
    }
}
