package com.bedu.work02.service;

import com.bedu.work02.exception.CurrencyAlreadyExistsException;
import com.bedu.work02.exception.CurrencyNotFoundException;
import com.bedu.work02.model.Currency;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private final Map<String, Currency> listCurrency;

    public CurrencyService() {
        Currency mxn = new Currency("MXN", 1.0);
        Currency uds = new Currency("USD", 0.056);
        Currency eur = new Currency("EUR", 0.052);
        listCurrency = new HashMap<>();
        listCurrency.put(mxn.getCurrency(), mxn);
        listCurrency.put(uds.getCurrency(), uds);
        listCurrency.put(eur.getCurrency(), eur);
    }

    public boolean exists(String id) {
        return listCurrency.containsKey(id);
    }

    public List<String> currency(String id) {
        if (!exists(id))
            throw new CurrencyNotFoundException(id);

        Currency currency = listCurrency.get(id);
        return listCurrency.values()
                .stream()
                .filter(c -> !Objects.equals(c.getCurrency(), id))
                .map(c -> c.getCurrency()
                        .concat(convertCurrencies(c,currency)))
                .collect(Collectors.toList());
    }

    public List<String> exchange(String id, Double amount) {
        if (!exists(id))
            throw new CurrencyNotFoundException(id);

        Currency currency = listCurrency.get(id);
        return listCurrency.values()
                .stream()
                .filter(c -> !Objects.equals(c.getCurrency(), id))
                .map(c -> c.getCurrency()
                        .concat(convertCurrencies(c,amount)))
                .collect(Collectors.toList());
    }

    public List<String> getAll() {
        return new ArrayList<>(listCurrency.keySet());
    }


    public Currency add(Currency Currency) {
        if (exists(Currency.getCurrency())) {
            throw new CurrencyAlreadyExistsException();
        }

        listCurrency.put(Currency.getCurrency().toUpperCase(), Currency);
        return Currency;
    }

    public void remove(String currency) {
        if (!exists(currency)) {
            throw new CurrencyNotFoundException(currency);
        }
        listCurrency.remove(currency);
    }


    public String convertCurrencies(Currency result, Currency refer){
        return " : ".concat(String.format("%.3f",result.getValue()/ refer.getValue()));
    }

    public String convertCurrencies(Currency refer, Double amount){
        return " : ".concat(String.format("%.3f", refer.getValue() * amount ));
    }

}
