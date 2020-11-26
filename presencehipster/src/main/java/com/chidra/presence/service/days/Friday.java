package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Friday extends EmployeeDay{

    final static int START_1 =Thursday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Friday(Row row) {
        super(START_1, row);
    }
}
