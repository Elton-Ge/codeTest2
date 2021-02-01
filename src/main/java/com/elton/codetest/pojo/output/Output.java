package com.elton.codetest.pojo.output;


import com.elton.codetest.pojo.input.Input;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Output  extends Input {
    private Double totalPrice;
    private List<Combination> bundlesCombination;
}
