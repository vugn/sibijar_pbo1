package com.sibijar.features.course_program;

public class CourseProgram {
    private int id_program_bimbel;
    private String jenis_program_bimbel;
    private int biaya;
    private int id_admin;

    public CourseProgram(int id_program_bimbel, String jenis_program_bimbel, int biaya, int id_admin) {
        this.id_program_bimbel = id_program_bimbel;
        this.jenis_program_bimbel = jenis_program_bimbel;
        this.biaya = biaya;
        this.id_admin = id_admin;
    }

    public int getId_program_bimbel() {
        return id_program_bimbel;
    }

    public void setId_program_bimbel(int id_program_bimbel) {
        this.id_program_bimbel = id_program_bimbel;
    }

    public String getJenis_program_bimbel() {
        return jenis_program_bimbel;
    }

    public void setJenis_program_bimbel(String jenis_program_bimbel) {
        this.jenis_program_bimbel = jenis_program_bimbel;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
}