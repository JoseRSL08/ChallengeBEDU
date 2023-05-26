package com.bedu.work02.controllers;

import com.bedu.work02.model.Currency;
import com.bedu.work02.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrenciesController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrenciesController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCurrencies() {
        return currencyService.getAll();
    }

    @GetMapping("/{currency}")
    public List<String> getCurrencyByKey(@PathVariable("currency") String currency) {
        return currencyService.currency(currency);
    }

    @PostMapping("/currency")
    public Currency addCurrency(@RequestBody Currency currency){
        return currencyService.add(currency);
    }


    @GetMapping("/{currency}/exchanges/{amount}")
    public List<String> getExchange(@PathVariable(name = "currency") String currency,
                                      @PathVariable(name = "amount") Double amount){
        return currencyService.exchange(currency,amount);
    }




}
