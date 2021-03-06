package com.bok.bank.helper.echange;

import com.bok.bank.model.ExchangeCurrencyValue;
import com.bok.bank.model.ExchangeCurrencyValueHistory;
import com.bok.bank.repository.ExchangeCurrencyValueHistoryRepository;
import com.bok.bank.repository.ExchangeCurrencyValueRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bok.bank.util.Constants.CURRENCIES_SAVED;

/**
 * API data : https://app.exchangerate-api.com/dashboard
 */
@Slf4j
@Component
public class ExchangeData {

    private final String LATEST = "/latest/";
    @Autowired
    ExchangeCurrencyValueHistoryRepository exchangeCurrencyValueHistoryRepository;
    @Autowired
    ExchangeCurrencyValueRepository exchangeCurrencyValueRepository;
    @Value("${scheduledFor}")
    private String scheduledFor;
    @Value("${exchange-currency.api-key}")
    private String apiKey;
    @Value("${exchange-currency.endpoint}")
    private String endpoint;

    /**
     * TEST METHOD
     * API GET https://v6.exchangerate-api.com/v6/ceb94d443f6398dd5e640cd1/latest/USD
     */
    @Deprecated
    public ExchangeCurrencyDTO fetchData() {

        try {
            return makeAPICall(endpoint + apiKey + LATEST + "USD");
        } catch (IOException e) {
            log.error("Error: cannont access content - " + e.toString());
        }
        return null;
    }

    /**
     * update currencies exchange and history value every 6H
     */
    @Scheduled(fixedDelay = 21600000, initialDelay = 1000)
    public void updateDatabaseCurrenciesExchange() {
        if (scheduledFor.equals("test")) {
            log.info("We are in test");
            return;
        }
        log.info("Updating exchange currencies table...");
        List<ExchangeCurrencyValueHistory> exchangeCurrencyValuesHistories = exchangeCurrencyValueHistoryRepository.findLastValueForAllCurrency();
        if (!exchangeCurrencyValuesHistories.isEmpty() && exchangeCurrencyValuesHistories.stream().allMatch(exchangeValue -> exchangeValue.getTime_next_update_unix().isAfter(Instant.now()))) {
            return;
        }
        List<ExchangeCurrencyValueHistory> exchangeCurrencyValueHistoryToSave = new ArrayList<>();
        for (String curr : CURRENCIES_SAVED) {
            try {
                ExchangeCurrencyDTO exchangeCurrencyDTO = makeAPICall(endpoint + apiKey + LATEST + curr);
                exchangeCurrencyValueHistoryToSave.add(exchangeCurrencyDTO.toExchangeCurrencyValues(curr));
            } catch (IOException e) {
                log.error("Error: cannont access content - " + e.toString());
            }
        }
        exchangeCurrencyValueHistoryRepository.saveAll(exchangeCurrencyValueHistoryToSave);
        updateCurrencyValue(exchangeCurrencyValueHistoryToSave);
    }

    /**
     * this method make the request and parse the data returned in an ExchangeCurrencyDTO object
     *
     * @param uri
     * @return
     * @throws IOException
     */
    private ExchangeCurrencyDTO makeAPICall(String uri) throws IOException {
        // Making Request
        URL url = new URL(uri);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObject = root.getAsJsonObject();


        Gson gson = new Gson();
        return gson.fromJson(jsonObject, ExchangeCurrencyDTO.class);
    }

    /**
     * Update if needed the currency value on db
     *
     * @param exchangeCurrencyValueHistories
     */
    private void updateCurrencyValue(List<ExchangeCurrencyValueHistory> exchangeCurrencyValueHistories) {
        log.info("updateCurrencyValue");
        Map<String, ExchangeCurrencyValueHistory> exchangeCurrencyValueHistoryMap = exchangeCurrencyValueHistories.stream().collect(Collectors.toMap(ExchangeCurrencyValueHistory::getBaseCurrency, Function.identity()));
        List<ExchangeCurrencyValue> exchangeCurrencyValues = exchangeCurrencyValueRepository.findAll();
        if (exchangeCurrencyValues.isEmpty() || exchangeCurrencyValues.size() < CURRENCIES_SAVED.size()) {
            exchangeCurrencyValueRepository.deleteAll();
            exchangeCurrencyValueRepository.saveAll(exchangeCurrencyValueHistories
                    .stream().map(ecvh -> new ExchangeCurrencyValue(ecvh.getTime_last_update_unix(), ecvh.getTime_next_update_unix(), ecvh.getBaseCurrency(), ecvh.getConversion_rates()))
                    .collect(Collectors.toList()));
            return;
        }
        exchangeCurrencyValues.forEach(exchangeCurrencyValue -> {
            exchangeCurrencyValue.setTime_last_update_unix(exchangeCurrencyValueHistoryMap.get(exchangeCurrencyValue.getBaseCurrency()).getTime_last_update_unix());
            exchangeCurrencyValue.setTime_next_update_unix(exchangeCurrencyValueHistoryMap.get(exchangeCurrencyValue.getBaseCurrency()).getTime_next_update_unix());
            exchangeCurrencyValue.setConversion_rates(exchangeCurrencyValueHistoryMap.get(exchangeCurrencyValue.getBaseCurrency()).getConversion_rates());
        });
        exchangeCurrencyValueRepository.saveAll(exchangeCurrencyValues);
    }
}
