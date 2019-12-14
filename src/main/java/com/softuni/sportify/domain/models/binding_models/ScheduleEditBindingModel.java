package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ScheduleEditBindingModel extends BaseBindingModel {

    private SportCenterEditBindingModel sportCenter;
    private int day;
    private int month;
    private int year;
    private List<EventEditBindingModel> time6;
    private List<EventEditBindingModel> time7;
    private List<EventEditBindingModel> time8;
    private List<EventEditBindingModel> time9;
    private List<EventEditBindingModel> time10;
    private List<EventEditBindingModel> time11;
    private List<EventEditBindingModel> time12;
    private List<EventEditBindingModel> time13;
    private List<EventEditBindingModel> time14;
    private List<EventEditBindingModel> time15;
    private List<EventEditBindingModel> time16;
    private List<EventEditBindingModel> time17;
    private List<EventEditBindingModel> time18;
    private List<EventEditBindingModel> time19;
    private List<EventEditBindingModel> time20;
    private List<EventEditBindingModel> time21;
    private List<EventEditBindingModel> time22;

    public ScheduleEditBindingModel() {
    }

    public SportCenterEditBindingModel getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenterEditBindingModel sportCenter) {
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

    public List<EventEditBindingModel> getTime6() {
        return time6;
    }

    public void setTime6(List<EventEditBindingModel> time6) {
        this.time6 = time6;
    }

    public List<EventEditBindingModel> getTime7() {
        return time7;
    }

    public void setTime7(List<EventEditBindingModel> time7) {
        this.time7 = time7;
    }

    public List<EventEditBindingModel> getTime8() {
        return time8;
    }

    public void setTime8(List<EventEditBindingModel> time8) {
        this.time8 = time8;
    }

    public List<EventEditBindingModel> getTime9() {
        return time9;
    }

    public void setTime9(List<EventEditBindingModel> time9) {
        this.time9 = time9;
    }

    public List<EventEditBindingModel> getTime10() {
        return time10;
    }

    public void setTime10(List<EventEditBindingModel> time10) {
        this.time10 = time10;
    }

    public List<EventEditBindingModel> getTime11() {
        return time11;
    }

    public void setTime11(List<EventEditBindingModel> time11) {
        this.time11 = time11;
    }

    public List<EventEditBindingModel> getTime12() {
        return time12;
    }

    public void setTime12(List<EventEditBindingModel> time12) {
        this.time12 = time12;
    }

    public List<EventEditBindingModel> getTime13() {
        return time13;
    }

    public void setTime13(List<EventEditBindingModel> time13) {
        this.time13 = time13;
    }

    public List<EventEditBindingModel> getTime14() {
        return time14;
    }

    public void setTime14(List<EventEditBindingModel> time14) {
        this.time14 = time14;
    }

    public List<EventEditBindingModel> getTime15() {
        return time15;
    }

    public void setTime15(List<EventEditBindingModel> time15) {
        this.time15 = time15;
    }

    public List<EventEditBindingModel> getTime16() {
        return time16;
    }

    public void setTime16(List<EventEditBindingModel> time16) {
        this.time16 = time16;
    }

    public List<EventEditBindingModel> getTime17() {
        return time17;
    }

    public void setTime17(List<EventEditBindingModel> time17) {
        this.time17 = time17;
    }

    public List<EventEditBindingModel> getTime18() {
        return time18;
    }

    public void setTime18(List<EventEditBindingModel> time18) {
        this.time18 = time18;
    }

    public List<EventEditBindingModel> getTime19() {
        return time19;
    }

    public void setTime19(List<EventEditBindingModel> time19) {
        this.time19 = time19;
    }

    public List<EventEditBindingModel> getTime20() {
        return time20;
    }

    public void setTime20(List<EventEditBindingModel> time20) {
        this.time20 = time20;
    }

    public List<EventEditBindingModel> getTime21() {
        return time21;
    }

    public void setTime21(List<EventEditBindingModel> time21) {
        this.time21 = time21;
    }

    public List<EventEditBindingModel> getTime22() {
        return time22;
    }

    public void setTime22(List<EventEditBindingModel> time22) {
        this.time22 = time22;
    }
}
