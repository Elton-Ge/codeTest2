package com.elton.codetest.service;

import com.elton.codetest.pojo.input.OrderItem;
import com.elton.codetest.pojo.output.FilledOrderItem;
import com.elton.codetest.pojo.standard.FormatList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesIO {

    private static final Logger logger = LogManager.getLogger(FilesIO.class);

    public static FormatList readConfig() {
//        File file = new File("src/main/resources/formats2.json");
        File file = new File("inputData/formats.json");
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        FormatList formatList = new Gson().fromJson(content, FormatList.class);
        return formatList;
    }

    public static List<OrderItem> readInput() {
        String dataSource = "inputData/input.txt";
        File file = new File(dataSource);
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                if (data.length() > 1) {
                    String[] s = data.split(" ");
                    orderItems.add(new OrderItem(Integer.valueOf(s[0]), s[1]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public static void write(List<FilledOrderItem> filledOrderItems) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson(filledOrderItems);
        File file = new File("output.json");
        try {
            FileUtils.write(file, string, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
