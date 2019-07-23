package com.example.smartgarage.database.model;

public class SpecialistTech {
    private int technicial_id;
    private int specialist_id;
    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTechnicial_id() {
        return technicial_id;
    }

    public void setTechnicial_id(int technicial_id) {
        this.technicial_id = technicial_id;
    }

    public int getSpecialist_id() {
        return specialist_id;
    }

    public void setSpecialist_id(int specialist_id) {
        this.specialist_id = specialist_id;
    }
}
