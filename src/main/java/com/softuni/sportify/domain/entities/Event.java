package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    private Sport sport;
    private String level;
    private int floor;
    private String hall;
    private int dayOfMonth;
    private int month;
    private int year;
    private String startTime;
    private int maxCapacity;

    public Event() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_id", nullable=false)
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Column(name = "level", nullable = false)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Min(-2)
    @Max(10)
    @Column(name = "floor", nullable = false)
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Size(min = 2, max = 30)
    @Column(name = "hall", nullable = false)
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @Column(name = "day_of_month", nullable = false)
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Column(name = "month", nullable = false)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "start_time", nullable = false)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Min(1)
    @Max(100)
    @Column(name = "max_capacity", nullable = false)
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}
