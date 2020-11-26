package com.chidra.presence.service;


import com.chidra.presence.service.days.*;

public class EmployeeWeelkyHours {

    private String category;
    private String employee;
    private String occupation;
    private int weeklyHours;
    private Monday monday;
    private Tuesday tuesday;
    private Wednesday wednesday;
    private Thursday thursday;
    private Friday friday;
    private Saturday saturday;
    private Sunday sunday;

    public EmployeeWeelkyHours() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public Monday getMonday() {
        return monday;
    }

    public void setMonday(Monday monday) {
        this.monday = monday;
    }

    public Tuesday getTuesday() {
        return tuesday;
    }

    public void setTuesday(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    public Wednesday getWednesday() {
        return wednesday;
    }

    public void setWednesday(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    public Thursday getThursday() {
        return thursday;
    }

    public void setThursday(Thursday thursday) {
        this.thursday = thursday;
    }

    public Friday getFriday() {
        return friday;
    }

    public void setFriday(Friday friday) {
        this.friday = friday;
    }

    public Saturday getSaturday() {
        return saturday;
    }

    public void setSaturday(Saturday saturday) {
        this.saturday = saturday;
    }

    public Sunday getSunday() {
        return sunday;
    }

    public void setSunday(Sunday sunday) {
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        return "com.bouacheria.chidra.EmployeeWeelkyHours{" +
                "category='" + category + '\'' +
                ", employee='" + employee + '\'' +
                ", occupation='" + occupation + '\'' +
                ", weeklyHours=" + weeklyHours +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                '}';
    }
}
