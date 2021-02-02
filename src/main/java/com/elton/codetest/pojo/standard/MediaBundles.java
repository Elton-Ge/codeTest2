package com.elton.codetest.pojo.standard;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class MediaBundles {
    private String formatName;
    private String code;
    private List<Bundle> bundles;

    public List<Integer> toQuantityArray() {
        return bundles.stream()
                .map(Bundle::getQuantity)
                .collect(Collectors.toList());
    }



    public Map<Integer, Double> priceMapper() {
        return bundles.stream().collect(Collectors.toMap(Bundle::getQuantity, Bundle::getPrice));

    }



}
