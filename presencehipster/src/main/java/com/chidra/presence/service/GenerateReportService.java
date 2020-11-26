package com.chidra.presence.service;

import com.chidra.presence.service.utils.ApplicationUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class GenerateReportService {

    private static final int MAX_ROW = 100;


    public List<String> generateReport() throws Exception {

        List<String> message = new ArrayList<>();
        message.add("starting. " + LocalDateTime.now().toString());
        List<EmployeeWeelkyHours> list = new LinkedList<>();
        Sheet sheet = getSheet();
        if(sheet==null){
            String error ="Aucun onglet ne contient 'print' dans son nom.";
            System.out.println(error);
            throw new RuntimeException(error);
        }

        int i = 0; // start on row 2
        for (Row row : sheet) {

            i++;
            if(i<2){
                continue;
            }

            boolean hasContent = RowParser.hasContent(row);

            if( i >= MAX_ROW){
                message.add("Max lignes atteintes. "+ MAX_ROW +". Considerer supprimer les lignes vides.");
                break;
            }

            if(!hasContent){
                System.out.println("Ligne: "+i +" est vide.");
                continue;
            }

            EmployeeWeelkyHours employeeWeelkyHours = new EmployeeWeelkyHours();

            RowParser.category(employeeWeelkyHours,row);
            RowParser.employee(employeeWeelkyHours,row);
            RowParser.occupation(employeeWeelkyHours,row);
            RowParser.weelkyHours(employeeWeelkyHours,row);
            RowParser.moday(employeeWeelkyHours,row);

            RowParser.tuesday(employeeWeelkyHours,row);
            RowParser.wednesday(employeeWeelkyHours,row);
            RowParser.thursday(employeeWeelkyHours,row);
            RowParser.friday(employeeWeelkyHours,row);
            RowParser.saturday(employeeWeelkyHours,row);
            RowParser.sunday(employeeWeelkyHours,row);

            list.add(employeeWeelkyHours);
            System.out.println(i + " "+ employeeWeelkyHours);
        }

        WriteExcel.write(list);
        message.add("done. " + LocalDateTime.now().toString());
        System.out.println("done !!!!!!!!!!");
        return message;
    }

    private Sheet getSheet() throws Exception {
        FileInputStream file = new FileInputStream(new File(ApplicationUtils.getInputputFileName()));
        Workbook workbook = new XSSFWorkbook(file);
        final int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            final Sheet sheetAt = workbook.getSheetAt(i);
            boolean isPrintable = sheetAt.getSheetName().toLowerCase().indexOf("print")!=-1;
            if(isPrintable){
                return sheetAt;
            }
        }
        return null;
    }
}
