package com.elton.codetest.pojo.standard;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Item {
    private String formatName;
    private String code;
    private List<Bundle> bundles;

    public ArrayList<Integer> toQuantityArray() {
        ArrayList<Integer> quantityArray = new ArrayList<Integer>();
        for (Bundle bundle : bundles) {
            quantityArray.add(bundle.getQuantity());
        }
        return quantityArray;
    }

    public HashMap<Integer, Double> priceMapper() {
        HashMap<Integer, Double> priceMapper = new HashMap<Integer, Double>();
        for (Bundle bundle : bundles) {
            priceMapper.put(bundle.getQuantity(), bundle.getPrice());
        }
        return priceMapper;
    }



}
