package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Wednesday extends EmployeeDay{

    final static int START_1 =Tuesday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Wednesday(Row row) {
        super(START_1, row);
    }
}
