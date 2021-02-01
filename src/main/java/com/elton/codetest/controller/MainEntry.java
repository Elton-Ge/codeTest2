package com.elton.codetest.controller;

import com.elton.codetest.pojo.input.Input;
import com.elton.codetest.pojo.output.Output;
import com.elton.codetest.pojo.standard.FormatList;
import com.elton.codetest.service.FilesIO;
import com.elton.codetest.service.GetOutput;

import java.util.List;
import java.util.stream.Collectors;

public class MainEntry {
    public static void main(String[] args) {
        new MainEntry().run();
    }

    public void run() {
        /* Initialize */
        FormatList formatList = FilesIO.readConfig();
        GetOutput generator = new GetOutput();
        generator.setFormat(formatList);

        /* GetInput */
        List<Input> inputs = FilesIO.readInput();

        /* Calculate */
        List<Output> outputs = inputs.stream()
                .map(generator::generateOutput)
                .collect(Collectors.toList());

        // GetOutput
        FilesIO.write(outputs);
        System.out.println("< Please check the final Output in the classpath: resources/output.json !!! >");
    }
}
