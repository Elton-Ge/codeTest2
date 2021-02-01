package com.elton.codetest;

import com.elton.codetest.pojo.input.Input;
import com.elton.codetest.service.FilesIO;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class TestReadFiles {


    @Test
    public void readInput() {
        FilesIO filesIO = new FilesIO();
        ArrayList<Input> real = filesIO.readInput();
        ArrayList<Input> expect = new ArrayList<>();
        expect.add(new Input(10,"IMG"));
        expect.add(new Input(15,"FLAC"));
        expect.add(new Input(13,"VID"));
        Assert.assertEquals(real, expect);
    }
}