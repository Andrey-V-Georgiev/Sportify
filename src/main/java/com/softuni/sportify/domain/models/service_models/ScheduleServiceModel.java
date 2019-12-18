package com.softuni.sportify.domain.models.service_models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ScheduleServiceModel extends BaseServiceModel {

    private SportCenterServiceModel sportCenter;
    private int day;
    private int month;
    private int year;
    private List<EventServiceModel> time6;
    private List<EventServiceModel> time7;
    private List<EventServiceModel> time8;
    private List<EventServiceModel> time9;
    private List<EventServiceModel> time10;
    private List<EventServiceModel> time11;
    private List<EventServiceModel> time12;
    private List<EventServiceModel> time13;
    private List<EventServiceModel> time14;
    private List<EventServiceModel> time15;
    private List<EventServiceModel> time16;
    private List<EventServiceModel> time17;
    private List<EventServiceModel> time18;
    private List<EventServiceModel> time19;
    private List<EventServiceModel> time20;
    private List<EventServiceModel> time21;
    private List<EventServiceModel> time22;

    public ScheduleServiceModel() {
    }


    @NotNull
    public SportCenterServiceModel getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenterServiceModel sportCenter) {
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

    @NotNull
    public List<EventServiceModel> getTime6() {
        return time6;
    }

    public void setTime6(List<EventServiceModel> time6) {
        this.time6 = time6;
    }

    @NotNull
    public List<EventServiceModel> getTime7() {
        return time7;
    }

    public void setTime7(List<EventServiceModel> time7) {
        this.time7 = time7;
    }

    @NotNull
    public List<EventServiceModel> getTime8() {
        return time8;
    }

    public void setTime8(List<EventServiceModel> time8) {
        this.time8 = time8;
    }

    @NotNull
    public List<EventServiceModel> getTime9() {
        return time9;
    }

    public void setTime9(List<EventServiceModel> time9) {
        this.time9 = time9;
    }

    @NotNull
    public List<EventServiceModel> getTime10() {
        return time10;
    }

    public void setTime10(List<EventServiceModel> time10) {
        this.time10 = time10;
    }

    @NotNull
    public List<EventServiceModel> getTime11() {
        return time11;
    }

    public void setTime11(List<EventServiceModel> time11) {
        this.time11 = time11;
    }

    @NotNull
    public List<EventServiceModel> getTime12() {
        return time12;
    }

    public void setTime12(List<EventServiceModel> time12) {
        this.time12 = time12;
    }

    @NotNull
    public List<EventServiceModel> getTime13() {
        return time13;
    }

    public void setTime13(List<EventServiceModel> time13) {
        this.time13 = time13;
    }

    @NotNull
    public List<EventServiceModel> getTime14() {
        return time14;
    }

    public void setTime14(List<EventServiceModel> time14) {
        this.time14 = time14;
    }

    @NotNull
    public List<EventServiceModel> getTime15() {
        return time15;
    }

    public void setTime15(List<EventServiceModel> time15) {
        this.time15 = time15;
    }

    @NotNull
    public List<EventServiceModel> getTime16() {
        return time16;
    }

    public void setTime16(List<EventServiceModel> time16) {
        this.time16 = time16;
    }

    @NotNull
    public List<EventServiceModel> getTime17() {
        return time17;
    }

    public void setTime17(List<EventServiceModel> time17) {
        this.time17 = time17;
    }

    @NotNull
    public List<EventServiceModel> getTime18() {
        return time18;
    }

    public void setTime18(List<EventServiceModel> time18) {
        this.time18 = time18;
    }

    @NotNull
    public List<EventServiceModel> getTime19() {
        return time19;
    }

    public void setTime19(List<EventServiceModel> time19) {
        this.time19 = time19;
    }

    @NotNull
    public List<EventServiceModel> getTime20() {
        return time20;
    }

    public void setTime20(List<EventServiceModel> time20) {
        this.time20 = time20;
    }

    @NotNull
    public List<EventServiceModel> getTime21() {
        return time21;
    }

    public void setTime21(List<EventServiceModel> time21) {
        this.time21 = time21;
    }

    @NotNull
    public List<EventServiceModel> getTime22() {
        return time22;
    }

    public void setTime22(List<EventServiceModel> time22) {
        this.time22 = time22;
    }
}
