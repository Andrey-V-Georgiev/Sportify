package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    private SportCenter sportCenter;
    private int day;
    private int month;
    private int year;
    private List<Event> time6;
    private List<Event> time7;
    private List<Event> time8;
    private List<Event> time9;
    private List<Event> time10;
    private List<Event> time11;
    private List<Event> time12;
    private List<Event> time13;
    private List<Event> time14;
    private List<Event> time15;
    private List<Event> time16;
    private List<Event> time17;
    private List<Event> time18;
    private List<Event> time19;
    private List<Event> time20;
    private List<Event> time21;
    private List<Event> time22;

    public Schedule() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_center_id", nullable = false)
    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

    @Min(1)
    @Max(31)
    @Column(name = "day", nullable = false)
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Min(1)
    @Max(12)
    @Column(name = "month", nullable = false)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Min(1990)
    @Max(2050)
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time6_id")
    public List<Event> getTime6() {
        return time6;
    }

    public void setTime6(List<Event> time6) {
        this.time6 = time6;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time7_id")
    public List<Event> getTime7() {
        return time7;
    }

    public void setTime7(List<Event> time7) {
        this.time7 = time7;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time8_id")
    public List<Event> getTime8() {
        return time8;
    }

    public void setTime8(List<Event> time8) {
        this.time8 = time8;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time9_id")
    public List<Event> getTime9() {
        return time9;
    }

    public void setTime9(List<Event> time9) {
        this.time9 = time9;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time10_id")
    public List<Event> getTime10() {
        return time10;
    }

    public void setTime10(List<Event> time10) {
        this.time10 = time10;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time11_id")
    public List<Event> getTime11() {
        return time11;
    }

    public void setTime11(List<Event> time11) {
        this.time11 = time11;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time12_id")
    public List<Event> getTime12() {
        return time12;
    }

    public void setTime12(List<Event> time12) {
        this.time12 = time12;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time13_id")
    public List<Event> getTime13() {
        return time13;
    }

    public void setTime13(List<Event> time13) {
        this.time13 = time13;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time14_id")
    public List<Event> getTime14() {
        return time14;
    }

    public void setTime14(List<Event> time14) {
        this.time14 = time14;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time15_id")
    public List<Event> getTime15() {
        return time15;
    }

    public void setTime15(List<Event> time15) {
        this.time15 = time15;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time16_id")
    public List<Event> getTime16() {
        return time16;
    }

    public void setTime16(List<Event> time16) {
        this.time16 = time16;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time17_id")
    public List<Event> getTime17() {
        return time17;
    }

    public void setTime17(List<Event> time17) {
        this.time17 = time17;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time18_id")
    public List<Event> getTime18() {
        return time18;
    }

    public void setTime18(List<Event> time18) {
        this.time18 = time18;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time19_id")
    public List<Event> getTime19() {
        return time19;
    }

    public void setTime19(List<Event> time19) {
        this.time19 = time19;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time20_id")
    public List<Event> getTime20() {
        return time20;
    }

    public void setTime20(List<Event> time20) {
        this.time20 = time20;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time21_id")
    public List<Event> getTime21() {
        return time21;
    }

    public void setTime21(List<Event> time21) {
        this.time21 = time21;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class)
    @JoinColumn(name = "time22_id")
    public List<Event> getTime22() {
        return time22;
    }

    public void setTime22(List<Event> time22) {
        this.time22 = time22;
    }
}
