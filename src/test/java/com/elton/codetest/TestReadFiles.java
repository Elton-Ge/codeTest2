package com.elton.codetest;

import com.elton.codetest.pojo.input.OrderItem;
import com.elton.codetest.service.FilesIO;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestReadFiles {

    @Test
    public void readInput() {
        FilesIO filesIO = new FilesIO();
        List<OrderItem> real = filesIO.readInput();
        List<OrderItem> expect = new ArrayList<>();
        expect.add(new OrderItem(10,"IMG"));
        expect.add(new OrderItem(15,"FLAC"));
        expect.add(new OrderItem(13,"VID"));
        Assert.assertEquals(real, expect);
    }
}