package com.elton.codetest.pojo.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Combination {
    private int number;
    private int bundle;
    private double unitPrice;
}
