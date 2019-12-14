package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ScheduleCreateBindingModel extends BaseBindingModel {

    private SportCenterCreateBindingModel sportCenter;
    private int day;
    private int month;
    private int year;
    private List<EventCreateBindingModel> time6;
    private List<EventCreateBindingModel> time7;
    private List<EventCreateBindingModel> time8;
    private List<EventCreateBindingModel> time9;
    private List<EventCreateBindingModel> time10;
    private List<EventCreateBindingModel> time11;
    private List<EventCreateBindingModel> time12;
    private List<EventCreateBindingModel> time13;
    private List<EventCreateBindingModel> time14;
    private List<EventCreateBindingModel> time15;
    private List<EventCreateBindingModel> time16;
    private List<EventCreateBindingModel> time17;
    private List<EventCreateBindingModel> time18;
    private List<EventCreateBindingModel> time19;
    private List<EventCreateBindingModel> time20;
    private List<EventCreateBindingModel> time21;
    private List<EventCreateBindingModel> time22;

    public ScheduleCreateBindingModel() {
    }

    public SportCenterCreateBindingModel getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenterCreateBindingModel sportCenter) {
        this.sportCenter = sportCenter;
    }

    @NotNull
    @Min(1)
    @Max(31)
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @NotNull
    @Min(1)
    @Max(12)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @NotNull
    @Min(1990)
    @Max(2050)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<EventCreateBindingModel> getTime6() {
        return time6;
    }

    public void setTime6(List<EventCreateBindingModel> time6) {
        this.time6 = time6;
    }

    public List<EventCreateBindingModel> getTime7() {
        return time7;
    }

    public void setTime7(List<EventCreateBindingModel> time7) {
        this.time7 = time7;
    }

    public List<EventCreateBindingModel> getTime8() {
        return time8;
    }

    public void setTime8(List<EventCreateBindingModel> time8) {
        this.time8 = time8;
    }

    public List<EventCreateBindingModel> getTime9() {
        return time9;
    }

    public void setTime9(List<EventCreateBindingModel> time9) {
        this.time9 = time9;
    }

    public List<EventCreateBindingModel> getTime10() {
        return time10;
    }

    public void setTime10(List<EventCreateBindingModel> time10) {
        this.time10 = time10;
    }

    public List<EventCreateBindingModel> getTime11() {
        return time11;
    }

    public void setTime11(List<EventCreateBindingModel> time11) {
        this.time11 = time11;
    }

    public List<EventCreateBindingModel> getTime12() {
        return time12;
    }

    public void setTime12(List<EventCreateBindingModel> time12) {
        this.time12 = time12;
    }

    public List<EventCreateBindingModel> getTime13() {
        return time13;
    }

    public void setTime13(List<EventCreateBindingModel> time13) {
        this.time13 = time13;
    }

    public List<EventCreateBindingModel> getTime14() {
        return time14;
    }

    public void setTime14(List<EventCreateBindingModel> time14) {
        this.time14 = time14;
    }

    public List<EventCreateBindingModel> getTime15() {
        return time15;
    }

    public void setTime15(List<EventCreateBindingModel> time15) {
        this.time15 = time15;
    }

    public List<EventCreateBindingModel> getTime16() {
        return time16;
    }

    public void setTime16(List<EventCreateBindingModel> time16) {
        this.time16 = time16;
    }

    public List<EventCreateBindingModel> getTime17() {
        return time17;
    }

    public void setTime17(List<EventCreateBindingModel> time17) {
        this.time17 = time17;
    }

    public List<EventCreateBindingModel> getTime18() {
        return time18;
    }

    public void setTime18(List<EventCreateBindingModel> time18) {
        this.time18 = time18;
    }

    public List<EventCreateBindingModel> getTime19() {
        return time19;
    }

    public void setTime19(List<EventCreateBindingModel> time19) {
        this.time19 = time19;
    }

    public List<EventCreateBindingModel> getTime20() {
        return time20;
    }

    public void setTime20(List<EventCreateBindingModel> time20) {
        this.time20 = time20;
    }

    public List<EventCreateBindingModel> getTime21() {
        return time21;
    }

    public void setTime21(List<EventCreateBindingModel> time21) {
        this.time21 = time21;
    }

    public List<EventCreateBindingModel> getTime22() {
        return time22;
    }

    public void setTime22(List<EventCreateBindingModel> time22) {
        this.time22 = time22;
    }
}
