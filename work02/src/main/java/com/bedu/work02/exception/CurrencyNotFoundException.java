package com.bedu.work02.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CurrencyNotFoundException extends RuntimeException{
    private String currency;
}
