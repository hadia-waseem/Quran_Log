package com.example.quran_log;

public class Log {
    public Log(Integer std_id, String name, String sabaq, String sabqi, String manzil, String date) {
        this.std_id = std_id;
        this.std_name = name;
        this.sabaq = sabaq;
        this.sabqi = sabqi;
        this.manzil = manzil;
        this.date = date;
    }

    private Integer std_id;
    private String std_name;
    private String sabaq;
    private String sabqi;
    private String manzil;
    private String date;

    public Log() {
    }

    public Integer getStd_id() {
        return std_id;
    }

    public void setStd_id(Integer std_id) {
        this.std_id = std_id;
    }

    public String getName() {
        return std_name;
    }

    public void setName(String name) {
        this.std_name = name;
    }

    public String getSabaq() {
        return sabaq;
    }

    public void setSabaq(String sabaq) {
        this.sabaq = sabaq;
    }

    public String getSabqi() {
        return sabqi;
    }

    public void setSabqi(String sabqi) {
        this.sabqi = sabqi;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Log{" +
                "std_id=" + std_id +
                ", name='" + std_name + '\'' +
                ", sabaq='" + sabaq + '\'' +
                ", sabqi='" + sabqi + '\'' +
                ", manzil='" + manzil + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
