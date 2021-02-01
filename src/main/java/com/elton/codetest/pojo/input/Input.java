package com.elton.codetest.pojo.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private int totalItems;
    private String formatCode;
}
