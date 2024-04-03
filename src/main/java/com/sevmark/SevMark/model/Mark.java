package com.sevmark.SevMark.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "marks")
public class Mark {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int dayOfMonth;
    private String monthYear;
    private String shortDay;
    private String hour;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    public Mark(Long id, int dayOfMonth, String monthYear, String shortDay, String hour, Local local) {
        this.id = id;
        this.dayOfMonth = dayOfMonth;
        this.monthYear = monthYear;
        this.shortDay = shortDay;
        this.hour = hour;
        this.local = local;
    }

    public Mark() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getShortDay() {
        return shortDay;
    }

    public void setShortDay(String shortDay) {
        this.shortDay = shortDay;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
