package com.chidra.presence.service.days;

import org.apache.poi.ss.usermodel.Row;

public class Sunday extends EmployeeDay{

    final static int START_1 =Saturday.START_1 +EmployeeDay.NEXT_DAYS_INDEX;
    public Sunday(Row row) {
        super(START_1, row);
    }
}
