package com.elton.codetest.service;


import com.elton.codetest.pojo.input.Input;
import com.elton.codetest.pojo.output.Combination;
import com.elton.codetest.pojo.output.Output;
import com.elton.codetest.pojo.standard.FormatList;
import com.elton.codetest.pojo.standard.Item;
import java.util.ArrayList;
import java.util.HashMap;

public class GetOutput {
    private HashMap<String, Item> itemMapper=new HashMap<>();

    public void setFormat(FormatList formatList) {
        formatList.getFormats().stream().forEach(
                item -> itemMapper.put(item.getCode(), item)
        );
    }

    public Output generateOutput(Input input){
        String formatCode = input.getFormatCode();
        Item item = itemMapper.get(formatCode);

        Calculate calculate = new Calculate();
        Output output = new Output();

        String itemName = input.getFormatCode();
        int total = input.getTotalItems();
        HashMap<Integer, Integer> combination = calculate.getCombination(item.toQuantityArray(), total);

        output.setTotalItems(total);
        output.setFormatCode(itemName);
        ArrayList<Combination> combinations = calculateUnitPrice(item, combination);
        output.setTotalPrice(getTotalPrice(combinations));
        output.setBundlesCombination(combinations);

        return output;
    }


    /* (item, unit price)*/
    private ArrayList<Combination> calculateUnitPrice(Item item, HashMap<Integer, Integer> combination){
        HashMap<Integer, Double> priceMapper = item.priceMapper();
        ArrayList<Combination> combinations = new ArrayList<>();
        for (Integer i : combination.keySet()) {
            Double unitPrice = priceMapper.get(i);
            Double totalPrice = unitPrice * combination.get(i);
            Combination unit = new Combination(combination.get(i), i, totalPrice);
            combinations.add(unit);
        }
        return combinations;
    }

    private double getTotalPrice(ArrayList<Combination> unitPriceSum){
        return unitPriceSum.stream()
                .mapToDouble(Combination::getUnitPrice)
                .sum();
    }





}
