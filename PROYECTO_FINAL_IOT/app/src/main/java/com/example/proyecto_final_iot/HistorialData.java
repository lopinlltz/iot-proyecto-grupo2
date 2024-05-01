package com.example.proyecto_final_iot;

public class HistorialData {
    private String activityName;
    private String supervisorName;
    private String date;
    private String hour;

    public HistorialData(String activityName, String supervisorName, String date, String hour) {
        this.activityName = activityName;
        this.supervisorName = supervisorName;
        this.date = date;
        this.hour = hour;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }
}
