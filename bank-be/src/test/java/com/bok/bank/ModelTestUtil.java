package com.bok.bank;

import com.bok.bank.model.Account;
import com.bok.bank.model.BankAccount;
import com.bok.bank.model.Card;
import com.bok.bank.model.Company;
import com.bok.bank.model.ExchangeCurrencyValue;
import com.bok.bank.model.ExchangeCurrencyValueHistory;
import com.bok.bank.model.User;
import com.bok.bank.repository.AccountRepository;
import com.bok.bank.repository.BankAccountRepository;
import com.bok.bank.repository.CardRepository;
import com.bok.bank.repository.ConfirmationEmailHistoryRepository;
import com.bok.bank.repository.ExchangeCurrencyValueHistoryRepository;
import com.bok.bank.repository.ExchangeCurrencyValueRepository;
import com.bok.bank.repository.TransactionRepository;
import com.bok.bank.util.Constants;
import com.bok.bank.util.CreditCardNumberGenerator;
import com.bok.bank.util.Generator;
import com.bok.bank.util.Money;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ModelTestUtil {
    public static final Faker faker = new Faker();
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency GBP = Currency.getInstance("GBP");
    public static final Currency USD = Currency.getInstance("USD");
    @Autowired
    CreditCardNumberGenerator creditCardNumberGenerator;
    @Autowired
    Generator generator;
    @Autowired
    ConfirmationEmailHistoryRepository confirmationEmailHistoryRepository;
    @Autowired
    ExchangeCurrencyValueHistoryRepository exchangeCurrencyValueHistoryRepository;
    @Autowired
    ExchangeCurrencyValueRepository exchangeCurrencyValueRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CardRepository cardRepository;

    public void populateDB() {
        List<Account> accounts = Arrays.asList(
                new User(4997L, "Domenico", "mico.fasano@gmail.com", "3926772950", "+39", Account.Status.ACTIVE, "Italy", "Apulia", "Locorotondo", "70010",
                        "via le mani dal naso", "12/c", null, "Fasano", User.Gender.M, "FSNDNC99C13D508Y", "Fasano", "Italy", LocalDate.of(1999, 3, 13).atStartOfDay().toInstant(ZoneOffset.UTC)),
                new User(4998L, "Chris", "chris.fara@gmail.com", "3911172950", "+39", Account.Status.ACTIVE, "Italy", "CalaBBria", "GIOIA TAURA", "00000",
                        "via le mani dal ferro", "69/c", "Gennaro", "Faraone", User.Gender.M, "CSTFRN99B44D508Y", "ViBBo Violenza", "Italy", LocalDate.of(1999, 8, 21).atStartOfDay().toInstant(ZoneOffset.UTC)),
                new User(4999L, "Paolo", "paolo.pio@gmail.com", "3911172150", "+39", Account.Status.ACTIVE, "Italy", "Apulia", "San Marco In Lamis", "00001",
                        "via le mani dai taralli", "00/c", "Pio", "Bevilacqua", User.Gender.M, "PLPBLQ99O99D508Y", "San Giovanni Rotondo", "Italy", LocalDate.of(1999, 10, 2).atStartOfDay().toInstant(ZoneOffset.UTC)),
                new Company(5000L, "Soldo", "soldo.soldo@soldo.com", "1234567890", "+39", Account.Status.ACTIVE, "Italy", "Lazio", "Rome", "00159",
                        "via le mani dal soldo", "2/c", "123422342"),
                new Company(5001L, "Softlab", "soft.lab@softlab.com", "1234562290", "+39", Account.Status.ACTIVE, "Italy", "Lazio", "Rome", "00159",
                        "via le mani dal bell stu sit", "13/c", "123499342")
        );
        accountRepository.saveAll(accounts);

        List<BankAccount> bankAccounts = Arrays.asList(
                new BankAccount(accounts.get(0).getId(), "1234543212345432123454321234", "firstBankAccount", "universitary", Currency.getInstance("EUR"), new Money(BigDecimal.ZERO), new Money(BigDecimal.valueOf(100)), BankAccount.Status.ACTIVE),
                new BankAccount(accounts.get(1).getId(), "1234543212311112123454321234", "AeroBankAccount", "boh", Currency.getInstance("EUR"), new Money(BigDecimal.ZERO), new Money(BigDecimal.valueOf(50)), BankAccount.Status.ACTIVE)
        );
        bankAccountRepository.saveAll(bankAccounts);

        populateCurrency();

    }

    public void populateCurrency() {
        Map<String, BigDecimal> currenciesEUR = new HashMap<>();
        currenciesEUR.put("USD", BigDecimal.valueOf(1.22));
        currenciesEUR.put("GBP", BigDecimal.valueOf(1.2));
        currenciesEUR.put("AMD", BigDecimal.valueOf(10.0));
        Map<String, BigDecimal> currenciesUSD = new HashMap<>();
        currenciesUSD.put("EUR", BigDecimal.valueOf(1.2));
        currenciesUSD.put("GBP", BigDecimal.valueOf(1.7));
        currenciesUSD.put("AMD", BigDecimal.valueOf(12.0));
        Map<String, BigDecimal> currenciesGBP = new HashMap<>();
        currenciesGBP.put("USD", BigDecimal.valueOf(1.2));
        currenciesGBP.put("EUR", BigDecimal.valueOf(1.5));
        currenciesGBP.put("AMD", BigDecimal.valueOf(15));
        List<ExchangeCurrencyValueHistory> exchangeCurrencyValueHistories = Arrays.asList(
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1616630403), Instant.ofEpochSecond(1616730403), "EUR", currenciesEUR),
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1616630402), Instant.ofEpochSecond(1616730402), "USD", currenciesUSD),
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1616630401), Instant.ofEpochSecond(1616730401), "GBP", currenciesGBP),
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1615630401), Instant.ofEpochSecond(1615740401), "EUR", currenciesEUR),
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1615630401), Instant.ofEpochSecond(1615740401), "USD", currenciesUSD),
                new ExchangeCurrencyValueHistory(Instant.ofEpochSecond(1615630401), Instant.ofEpochSecond(1615740401), "GBP", currenciesGBP));
        exchangeCurrencyValueHistoryRepository.saveAll(exchangeCurrencyValueHistories);

        Map<String, BigDecimal> currenciesEUR1 = new HashMap<>();
        currenciesEUR1.put("EUR", BigDecimal.valueOf(1.000));
        currenciesEUR1.put("USD", BigDecimal.valueOf(1.22));
        currenciesEUR1.put("GBP", BigDecimal.valueOf(0.86));
        currenciesEUR1.put("AMD", BigDecimal.valueOf(634.98));
        Map<String, BigDecimal> currenciesUSD1 = new HashMap<>();
        currenciesEUR1.put("USD", BigDecimal.valueOf(1.000));
        currenciesUSD1.put("EUR", BigDecimal.valueOf(0.82));
        currenciesUSD1.put("GBP", BigDecimal.valueOf(0.71));
        currenciesUSD1.put("AMD", BigDecimal.valueOf(552.37));
        Map<String, BigDecimal> currenciesGBP1 = new HashMap<>();
        currenciesEUR1.put("GBP", BigDecimal.valueOf(1.000));
        currenciesGBP1.put("USD", BigDecimal.valueOf(1.41));
        currenciesGBP1.put("EUR", BigDecimal.valueOf(1.16));
        currenciesGBP1.put("AMD", BigDecimal.valueOf(738.70));
        List<ExchangeCurrencyValue> exchangeCurrencyValues = Arrays.asList(
                new ExchangeCurrencyValue(Instant.now(), Instant.now(), "EUR", currenciesEUR1),
                new ExchangeCurrencyValue(Instant.now(), Instant.now(), "USD", currenciesUSD1),
                new ExchangeCurrencyValue(Instant.now(), Instant.now(), "GBP", currenciesGBP1));
        exchangeCurrencyValueRepository.saveAll(exchangeCurrencyValues);
    }

    public void clearAll() {
        transactionRepository.deleteAll();
        cardRepository.deleteAll();
        confirmationEmailHistoryRepository.deleteAll();
        bankAccountRepository.deleteAll();
        accountRepository.deleteAll();
        exchangeCurrencyValueHistoryRepository.deleteAll();
        exchangeCurrencyValueRepository.deleteAll();
        populateCurrency();
    }

    public User createAndSaveUser(Long accountId) {
        User user = new User(accountId, faker.name().firstName(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(), "+39",
                Account.Status.ACTIVE, faker.address().country(), "RD", faker.address().city(), faker.address().zipCode(), faker.address().streetAddress(), faker.address().streetAddressNumber(), "",
                faker.name().lastName(), User.Gender.M, faker.name().firstName().toUpperCase().substring(0, 3) + faker.name().lastName().toUpperCase().substring(0, 3) + "99" + "D" + faker.date().birthday().getDay() + "D512Y", faker.country().capital(), faker.country().name(), faker.date().birthday().toInstant());
        return accountRepository.save(user);
    }

    public Company createAndSaveCompany(Long accountId) {
        Company company = new Company(accountId, faker.company().name(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), "+39",
                Account.Status.ACTIVE, faker.address().country(), "RD", faker.address().city(), faker.address().zipCode(), faker.address().streetAddress(), faker.address().streetAddressNumber(),
                faker.idNumber().valid());
        return accountRepository.save(company);
    }

    public BankAccount createAndSaveBankAccount(Account account) {
        Currency currency = Currency.getInstance(faker.currency().code());
        BankAccount bankAccount = new BankAccount(account.getId(), generator.generateIBAN(), faker.funnyName().name(), faker.lorem().paragraph(), currency, new Money(BigDecimal.ZERO, currency), new Money(BigDecimal.valueOf(100), currency), BankAccount.Status.ACTIVE);
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount createAndSaveBankAccount(Account account, Currency currency) {
        BankAccount bankAccount = new BankAccount(account.getId(), generator.generateIBAN(), faker.funnyName().name(), faker.lorem().characters(5, 10), currency, new Money(BigDecimal.ZERO, currency), new Money(BigDecimal.valueOf(100), currency), BankAccount.Status.ACTIVE);
        return bankAccountRepository.save(bankAccount);
    }


    public Card createAndSaveActiveCard(Account account, BankAccount bankAccount) {
        Card newCard = new Card(faker.name().title(), account, Card.CardStatus.ACTIVE, Card.Type.DEBIT, Instant.now().plus(Period.ofYears(4).getDays(), ChronoUnit.DAYS),
                creditCardNumberGenerator.generateToken(), faker.lorem().characters(4, 10), creditCardNumberGenerator.generate(Constants.BIN_BOK, 15), bankAccount, creditCardNumberGenerator.generateCvv(), creditCardNumberGenerator.generatePIN());
        return cardRepository.saveAndFlush(newCard);
    }
}
