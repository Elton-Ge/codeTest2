package com.elton.codetest.pojo.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bundle {
    private int quantity;
    private double price;
}
