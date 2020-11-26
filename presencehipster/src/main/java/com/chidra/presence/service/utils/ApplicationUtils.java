package com.chidra.presence.service.utils;


import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationUtils {

    private static String INPUT_FILE_NAME = "planning";
    private static String OUTPUT_FILE_NAME = "presence";
    private static String FILE_NAME_PATTERN = "yyyy-MMM-dd-HH-mm-ss";
    private static SimpleDateFormat FILE_NAME_DATE_FORMAT = new SimpleDateFormat(FILE_NAME_PATTERN);

    private static String TIME_PATTERN = "HH:mm";
    private static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_PATTERN);

    private final static String USER_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString();

    public static String getOutputFileName(){

        String time =USER_DIRECTORY +File.separator+ OUTPUT_FILE_NAME + FILE_NAME_DATE_FORMAT.format(new Date()) +".xlsx";

        return time;
    }

    public static String getInputputFileName(){

        String time =USER_DIRECTORY +File.separator+ INPUT_FILE_NAME +".xlsx";

        return time;
    }

    public static String getTime(Date date){
        return TIME_FORMAT.format(date);
    }
}
