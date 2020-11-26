package com.chidra.presence.service;

import com.chidra.presence.service.days.*;
import org.apache.poi.ss.usermodel.Row;

public class RowParser {

    private static int INDEX = 0;
    private final static int CATEGORY_COL = INDEX++;
    private final static int EMPLOYE_COL  = INDEX++;
    private final static int OCCUPATION_COL = INDEX++;
    private final static int WEEKLY_HOUR_COL = INDEX++;

    public static EmployeeWeelkyHours category(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        employeeWeelkyHours.setCategory(row.getCell(CATEGORY_COL).getStringCellValue());
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours employee(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        employeeWeelkyHours.setEmployee(row.getCell(EMPLOYE_COL).getStringCellValue());
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours occupation(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        employeeWeelkyHours.setOccupation(row.getCell(OCCUPATION_COL).getStringCellValue());
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours weelkyHours(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        double cellValue= row.getCell(WEEKLY_HOUR_COL).getNumericCellValue();
        int value = (int)cellValue;
        employeeWeelkyHours.setWeeklyHours(value);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours moday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Monday day = new Monday(row);
        employeeWeelkyHours.setMonday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours tuesday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Tuesday day = new Tuesday(row);
        employeeWeelkyHours.setTuesday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours wednesday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Wednesday day = new Wednesday(row);
        employeeWeelkyHours.setWednesday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours thursday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Thursday day = new Thursday(row);
        employeeWeelkyHours.setThursday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours friday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Friday day = new Friday(row);
        employeeWeelkyHours.setFriday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours saturday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Saturday day = new Saturday(row);
        employeeWeelkyHours.setSaturday(day);
        return employeeWeelkyHours;
    }

    public static EmployeeWeelkyHours sunday(EmployeeWeelkyHours employeeWeelkyHours, Row row){
        Sunday day = new Sunday(row);
        employeeWeelkyHours.setSunday(day);
        return employeeWeelkyHours;
    }

    public static boolean hasContent(Row row) {
        final String stringCellValue = row.getCell(0).getStringCellValue();
        return stringCellValue!=null && stringCellValue.trim().length()>0;
    }
}
