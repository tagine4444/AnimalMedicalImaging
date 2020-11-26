package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Saturday extends EmployeeDay{

    final static int START_1 =Friday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Saturday(Row row) {
        super(START_1, row);
    }
}
