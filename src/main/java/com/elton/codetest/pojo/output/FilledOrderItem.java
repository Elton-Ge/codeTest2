package com.elton.codetest.pojo.output;


import com.elton.codetest.pojo.input.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FilledOrderItem extends OrderItem {
    private Double totalPrice;
    private List<Combination> bundlesCombination;
}
