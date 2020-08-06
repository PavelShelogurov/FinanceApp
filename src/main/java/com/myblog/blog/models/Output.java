package com.myblog.blog.models;

public class Output {
    //этот класс предназначен для хранения данных переда выводом на экран в разделе статистика
    private String monthField;
    private int incomingField;
    private int costField;

    public Output (int monthField, int incomingField, int costField){
        //this.monthField  = monthField;
        this.incomingField = incomingField;
        this.costField = costField;
        switch (monthField){
            case 1:
                this.monthField = "Январь";
                break;
            case 2:
                this.monthField = "Февраль";
                break;
            case 3:
                this.monthField = "Март";
                break;
            case 4:
                this.monthField = "Апрель";
                break;
            case 5:
                this.monthField = "Май";
                break;
            case 6:
                this.monthField = "Июнь";
                break;
            case 7:
                this.monthField = "Июль";
                break;
            case 8:
                this.monthField = "Август";
                break;
            case 9:
                this.monthField = "Сентябрь";
                break;
            case 10:
                this.monthField = "Октябрь";
                break;
            case 11:
                this.monthField = "Ноябрь";
                break;
            case 12:
                this.monthField = "Декабрь";
                break;

        }
    }

    public String getMonthField() {
        return monthField;
    }

    public void setMonthField(String monthField) {
        this.monthField = monthField;
    }

    public int getIncomingField() {
        return incomingField;
    }

    public void setIncomingField(int incomingField) {
        this.incomingField = incomingField;
    }

    public int getCostField() {
        return costField;
    }

    public void setCostField(int costField) {
        this.costField = costField;
    }
}
