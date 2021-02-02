package com.elton.codetest;

import com.elton.codetest.pojo.input.OrderItem;
import com.elton.codetest.pojo.output.FilledOrderItem;
import com.elton.codetest.pojo.standard.FormatList;
import com.elton.codetest.service.FilesIO;
import com.elton.codetest.service.OrderFiller;

import java.util.List;
import java.util.stream.Collectors;

public class MainEntry {
    public static void main(String[] args) {
        new MainEntry().run();
    }

    public void run() {
        /* Initialize */
        FormatList formatList = FilesIO.readConfig();
        OrderFiller generator = new OrderFiller();
        generator.setFormat(formatList);

        /* GetInput */
        List<OrderItem> orderItems = FilesIO.readInput();

        /* Calculate */
        List<FilledOrderItem> filledOrderItems = orderItems.stream()
                .map(generator::generateOutput)
                .collect(Collectors.toList());

        /* Generate Output */
        FilesIO.write(filledOrderItems);
        System.out.println("< Please check the final Output under root directory: output.json !!! >");
    }
}
