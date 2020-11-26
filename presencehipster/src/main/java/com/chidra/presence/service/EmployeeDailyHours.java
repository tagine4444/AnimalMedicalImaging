package com.chidra.presence.service;

import com.chidra.presence.service.days.EmployeeDay;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDailyHours {


    private String category;
    private String employee;
    private String occupation;
    private int weeklyHours;
    private EmployeeDay aDay;

    public EmployeeDailyHours(String category, String employee, String occupation, int weeklyHours, EmployeeDay aDay) {
        this.category = category;
        this.employee = employee;
        this.occupation = occupation;
        this.weeklyHours = weeklyHours;
        this.aDay = aDay;
    }


    public String getCategory() {
        return category;
    }

    public String getEmployee() {
        return employee;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public EmployeeDay getaDay() {
        return aDay;
    }

    public static List<EmployeeDailyHours> getMonday(List<EmployeeWeelkyHours> list){

       return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getMonday()))
                .collect(Collectors.toList());
    }

    public static List<EmployeeDailyHours> getTuesday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getTuesday()))
                .collect(Collectors.toList());
    }

    public static List<EmployeeDailyHours> getWednesday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getWednesday()))
                .collect(Collectors.toList());
    }

    public static List<EmployeeDailyHours> getThursday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getThursday()))
                .collect(Collectors.toList());
    }

    public static List<EmployeeDailyHours> getFriday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getFriday()))
                .collect(Collectors.toList());
    }

    public static List<EmployeeDailyHours> getSaturday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getSaturday()))
                .collect(Collectors.toList());
    }
    public static List<EmployeeDailyHours> getSunday(List<EmployeeWeelkyHours> list){

        return list.stream().map(employeeWeelkyHour ->
                new EmployeeDailyHours(employeeWeelkyHour.getCategory(),
                        employeeWeelkyHour.getEmployee(),
                        employeeWeelkyHour.getOccupation(),
                        employeeWeelkyHour.getWeeklyHours(), employeeWeelkyHour.getSunday()))
                .collect(Collectors.toList());
    }
}
