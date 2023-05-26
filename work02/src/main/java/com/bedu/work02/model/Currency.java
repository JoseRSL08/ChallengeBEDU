package com.bedu.work02.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {


    @Pattern(regexp = "^[^0-9]{3}$")
    @NotBlank
    private String currency;


    private double value;

}
