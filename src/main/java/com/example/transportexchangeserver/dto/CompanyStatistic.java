package com.example.transportexchangeserver.dto;

public class CompanyStatistic {
    private String company;

    private double points;

    public CompanyStatistic(String company, double points) {
        this.company = company;
        this.points = points;
    }

    public String getCompany() {
        return company;
    }

    public double getPoints() {
        return points;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
