package com.elton.codetest.service;

import com.elton.codetest.pojo.input.Input;
import com.elton.codetest.pojo.output.Output;
import com.elton.codetest.pojo.standard.FormatList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesIO {

    public static FormatList readConfig() {
        File file = new File("src/main/resources/formats.json");
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FormatList formatList = new Gson().fromJson(content, FormatList.class);
        return formatList;
    }

    public static ArrayList<Input> readInput() {
        String dataSource = "src/main/resources/input.txt";
        File file = new File(dataSource);
        ArrayList<Input> inputs = new ArrayList<Input>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                if (data.length() > 1) {
                    String[] s = data.split(" ");
                    inputs.add(new Input(Integer.valueOf(s[0]), s[1]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static void write(List<Output> outputs) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson(outputs);
        File file = new File("src/main/resources/output.json");
        try {
            FileUtils.write(file, string, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
