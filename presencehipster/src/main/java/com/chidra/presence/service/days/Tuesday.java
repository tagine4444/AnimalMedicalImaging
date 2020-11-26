package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Tuesday extends EmployeeDay{

    final static int START_1 =Monday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Tuesday(Row row) {
        super(START_1, row);
    }
}
