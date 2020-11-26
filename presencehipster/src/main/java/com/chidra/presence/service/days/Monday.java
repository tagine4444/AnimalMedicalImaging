package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Monday extends EmployeeDay{

    final static int START_1 =4;
    public Monday(Row row) {
        super(START_1, row);
    }
}
