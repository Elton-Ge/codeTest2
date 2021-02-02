package com.elton.codetest.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


public class Calculator {
    public Map<Integer, Integer> getCombination(List<Integer> category, int num) {
        Stack<ItemStack> stack = new Stack<>();
        List<ItemStack> visited = new ArrayList<>();
        stack.push(new ItemStack(num, null));
        while (true) {
            if (stack.isEmpty()) {
                break;
            }
            ItemStack item = stack.pop();
            if (!visited.contains(item)) {
                if (category.contains(item.num)) {
                    return resultMapper(getResultFromParents(item));
                }
                visited.add(item);
            }
            List<ItemStack> newItems = getNewItems(item, category);
            newItems.forEach(newItem -> stack.push(newItem));
        }
        return null;
    }


    private Map<Integer, Integer> resultMapper(List<Integer> result) {
        Map<Integer, Integer> mapper = new HashMap<>();
        for (Integer i : result) {
            if (mapper.get(i) == null) {
                mapper.put(i, 0);
            }
            mapper.put(i, mapper.get(i) + 1);
        }
        return mapper;
    }

    private List<Integer> getResultFromParents(ItemStack item) {
        int num = item.num;
        List<Integer> result = new ArrayList<>();
        while (item.getParents() != null) {
            result.add(item.parents.get(0).num - item.num);
            item = item.getParents().get(0);
        }
        result.add(num);
        return result;
    }


    private List<ItemStack> getNewItems(ItemStack currentItem, List<Integer> category) {
        List<ItemStack> newItems = new ArrayList<>();
        for (Integer i : category) {
            if (currentItem.num > i) {
                List<ItemStack> p = new ArrayList<>();
                p.add(currentItem);
                newItems.add(new ItemStack(currentItem.num - i, p));
            }
        }
        return newItems;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ItemStack {
        private int num;
        private List<ItemStack> parents;
    }

}
