package com.sevmark.SevMark.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "availableTimes")
public class AvailableTimes {
    
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
    
    public AvailableTimes(Long id, String day, String startTime, String endTime, Local local) {
        this.id = id;
        this.day = day;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.local = local;
    }

    public AvailableTimes() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public String getStartTimeFormatted() {
        if (startTime != null) {
            return startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return null;
    }

    // Getter para endTime formatado como "HH:mm"
    public String getEndTimeFormatted() {
        if (endTime != null) {
            return endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return null;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public Local getLocal() {
        return local;
    }
    public void setLocal(Local local) {
        this.local = local;
    }
}
