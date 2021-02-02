package com.elton.codetest.service;

import com.elton.codetest.pojo.input.OrderItem;
import com.elton.codetest.pojo.output.Combination;
import com.elton.codetest.pojo.output.FilledOrderItem;
import com.elton.codetest.pojo.standard.FormatList;
import com.elton.codetest.pojo.standard.MediaBundles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFiller {
    private Map<String, MediaBundles> itemMapper = new HashMap<>();

    public void setFormat(FormatList formatList) {
        formatList.getFormats().stream().forEach(
                mediaBundles -> itemMapper.put(mediaBundles.getCode(), mediaBundles)
        );
    }

    public FilledOrderItem generateOutput(OrderItem orderItem) {
        String formatCode = orderItem.getFormatCode();
        MediaBundles mediaBundles = itemMapper.get(formatCode);

        Calculator calculator = new Calculator();
        FilledOrderItem filledOrderItem = new FilledOrderItem();

        String itemName = orderItem.getFormatCode();
        int total = orderItem.getTotalItems();
        Map<Integer, Integer> combination = calculator.getCombination(mediaBundles.toQuantityArray(), total);

        filledOrderItem.setTotalItems(total);
        filledOrderItem.setFormatCode(itemName);
        List<Combination> combinations = calculateUnitPrice(mediaBundles, combination);
        filledOrderItem.setTotalPrice(getTotalPrice(combinations));
        filledOrderItem.setBundlesCombination(combinations);

        return filledOrderItem;
    }


    /* calculate unit price*/
    private List<Combination> calculateUnitPrice(MediaBundles mediaBundles, Map<Integer, Integer> combination) {
        Map<Integer, Double> priceMapper = mediaBundles.priceMapper();
        List<Combination> combinations = new ArrayList<>();
        combination.keySet().forEach(i -> {
            Double unitPrice = priceMapper.get(i);
            Double totalPrice = unitPrice * combination.get(i);
            Combination unit = new Combination(combination.get(i), i, totalPrice);
            combinations.add(unit);
        });
        return combinations;
    }

    private double getTotalPrice(List<Combination> unitPriceSum) {
        return unitPriceSum.stream()
                .mapToDouble(Combination::getUnitPrice)
                .sum();
    }


}
