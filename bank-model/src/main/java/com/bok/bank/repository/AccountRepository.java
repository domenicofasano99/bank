package com.bok.bank.repository;

import com.bok.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a.email as email, a.icc as icc, a.mobile as mobile, a.status as status, a.type as type, a.name as name, a.middleName as middleName, a.surname as surname FROM Account a WHERE a.id = :accountId")
    Optional<Projection.AccountInfo> findAccountInfoByAccountId(Long accountId);

    @Query("SELECT a.type FROM Account a where a.id = :accountId")
    Account.Type findAccountTypeById(Long accountId);

    @Query("select count(a) > 0 from Account a where a.taxCode = :code or a.vatNumber = :code")
    boolean existsByVatNumberOrTaxCode(String code);

    public class Projection {
        public interface AccountInfo {

            String getEmail();

            String getIcc();

            String getMobile();

            Account.Status getStatus();

            Account.Type getType();

            String getName();

            String getMiddleName();

            String getSurname();
        }
    }

}
