package com.myblog.blog.models;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int incoming;
    private int cost;

    private int day;
    private int month;
    private int year;

    public Cash(String incoming, String cost) {
        //защита от пустых строк (работает не полностью)
        if(incoming.trim().length()== 0){
            this.cost =Integer.parseInt(cost);
            this.incoming = 0 ;
            System.out.println("Сработало 'пустая трата'");
        }
         else if(cost.trim().length()==0){
            this.incoming =Integer.parseInt(incoming);
            this.cost = 0 ;
            System.out.println("Сработало 'пустай заработок'");
        }else {
            this.incoming = Integer.parseInt(incoming);
            this.cost = Integer.parseInt(cost);
        }

        Calendar dateNow = new GregorianCalendar();
        SimpleDateFormat dayNow = new SimpleDateFormat("dd");
        SimpleDateFormat monthNow = new SimpleDateFormat("M");
        SimpleDateFormat yearNow = new SimpleDateFormat("YYYY");
        day =Integer.parseInt(dayNow.format(dateNow.getTime()));
        month =Integer.parseInt(monthNow.format(dateNow.getTime()));
        year =Integer.parseInt(yearNow.format(dateNow.getTime()));

    }

    public Cash() {
    }

    public int getIncoming() {
        return incoming;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
