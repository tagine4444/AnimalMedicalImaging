package com.chidra.presence.service.days;

import com.chidra.presence.service.utils.ApplicationUtils;
import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

public abstract class EmployeeDay {

    static final int NEXT_DAYS_INDEX=7;

    protected int index;
    protected int start1Index;
    protected int end1Index;

    protected int start2Index;
    protected int end2Index;
    protected int totalIndex;

    protected String startTime1 = null;
    protected String endTime1 = null;

    protected String startTime2 = null;
    protected String endTime2 = null;

    protected String totalHours;


    public EmployeeDay(int index, Row row) {
        this.index = index;
        this.start1Index = index;
        this.end1Index = ++index;
        int repas = ++index;
        this.start2Index = ++index;
        this.end2Index = ++index;
        this.totalIndex = ++index;

        this.startTime1 = getTime(row, start1Index);
        this.endTime1 = getTime(row, end1Index);
        this.startTime2 = getTime(row, start2Index);
        this.endTime2 = getTime(row, end2Index);

        this.totalHours =getTime(row,totalIndex);
    }

    String getTime(Row row, int index){
        final Date dateCellValue = row.getCell(index).getDateCellValue();
        if( dateCellValue==null){
            return "";
        }

        return ApplicationUtils.getTime(dateCellValue);
    }

    public String getTrancheHorraire(){

        StringBuilder sb = new StringBuilder();
        if(!isEmpty(startTime1) && !isEmpty(endTime1)){
            sb.append(startTime1 +"-"+ endTime1);
        }
        if(sb.length()>0){
            sb.append(" / ");
        }
        if(!isEmpty(startTime2) && !isEmpty(endTime2)){
            sb.append(startTime2 +"-"+ endTime2);
        }
        return sb.toString();
    }

    public String getTotalHours() {
        return totalHours;
    }

    @Override
    public String toString() {
        return "Day{" + getTrancheHorraire() +" "+ totalHours + " hours}";
    }


    boolean isEmpty(String str){
        if(str==null|| str.length()==0){
            return true;
        }
        return false;
    }
}
