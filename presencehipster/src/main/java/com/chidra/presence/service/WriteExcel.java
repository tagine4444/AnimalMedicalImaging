package com.chidra.presence.service;

import com.chidra.presence.service.utils.ApplicationUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcel {


    public static void write(List<EmployeeWeelkyHours> list){
        XSSFWorkbook workbook = new XSSFWorkbook();
        final List<EmployeeDailyHours> mondayList  = EmployeeDailyHours.getMonday(list);
        final List<EmployeeDailyHours> tuesdayList = EmployeeDailyHours.getTuesday(list);
        final List<EmployeeDailyHours> wednesdayList = EmployeeDailyHours.getWednesday(list);


        writeADay(mondayList, workbook, "Lundi");
        writeADay(tuesdayList, workbook, "Mardi");
        writeADay(wednesdayList, workbook, "Mercredi");

        try {
            String fileName = ApplicationUtils.getOutputFileName();
            System.out.println("creating file: "+ fileName);
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeADay(List<EmployeeDailyHours> list , XSSFWorkbook workbook, String sheetName){

        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowNum = 0;
        System.out.println("generation de l' onglet "+ sheetName);


        Row header = sheet.createRow(rowNum++);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Occupation");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Employe");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Tranche Horraire");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Heures");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Signature");
        headerCell.setCellStyle(headerStyle);

        for (EmployeeDailyHours employeeWeelkyHours : list) {
            Row newRow = sheet.createRow(rowNum++);
            Cell newCell1 = newRow.createCell(0);
            newCell1.setCellValue(employeeWeelkyHours.getOccupation());

            Cell newCell2 = newRow.createCell(1);
            newCell2.setCellValue(employeeWeelkyHours.getEmployee());

            Cell newCell3 = newRow.createCell(2);
            newCell3.setCellValue(employeeWeelkyHours.getaDay().getTrancheHorraire());

            Cell newCell4 = newRow.createCell(3);
            newCell4.setCellValue(employeeWeelkyHours.getaDay().getTotalHours());

            Cell newCell5 = newRow.createCell(4);
            newCell5.setCellValue("");
        }

    }
}
