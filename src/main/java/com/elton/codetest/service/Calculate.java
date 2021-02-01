package com.elton.codetest.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Calculate {
    public HashMap<Integer, Integer> getCombination(ArrayList<Integer> category, int num) {
        Stack<ItemStack> stack = new Stack<>();
        ArrayList<ItemStack> visited = new ArrayList<>();
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
            ArrayList<ItemStack> newItems = getNewItems(item, category);
            pushStack(stack, newItems);
        }
        return null;
    }

    private HashMap<Integer, Integer> resultMapper(ArrayList<Integer> result) {
        HashMap<Integer, Integer> mapper = new HashMap<>();
        for (Integer i : result) {
            if (mapper.get(i) == null) {
                mapper.put(i, 0);
            }
            mapper.put(i, mapper.get(i) + 1);
        }
        return mapper;
    }

    private ArrayList<Integer> getResultFromParents(ItemStack item) {
        int num = item.num;
        ArrayList<Integer> result = new ArrayList<>();
        while (item.getParents() != null) {
            result.add(item.parents.get(0).num - item.num);
            item = item.getParents().get(0);
        }
        result.add(num);
        return result;
    }

    private void pushStack(Stack<ItemStack> stack, ArrayList<ItemStack> newItems) {
        for (ItemStack newItem : newItems) {
            stack.push(newItem);
        }
    }

    private ArrayList<ItemStack> getNewItems(ItemStack currentItem, ArrayList<Integer> category) {
        ArrayList<ItemStack> newItems = new ArrayList<>();
        for (Integer i : category) {
            if (currentItem.num > i) {
                ArrayList<ItemStack> p = new ArrayList<>();
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
        private ArrayList<ItemStack> parents;
    }

}
