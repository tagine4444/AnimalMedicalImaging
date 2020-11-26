package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Thursday extends EmployeeDay{

    final static int START_1 =Wednesday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Thursday(Row row) {
        super(START_1, row);
    }
}
