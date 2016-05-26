package com.example.bohdan.flowerkievua.utils;

import android.content.Context;

import java.util.List;

/**
 * Created by User on 23-May-16.
 */
public class Flowers {
    private String flowersName;
    private String flowersImageUrl;
    private String flowersCost;
    private String flowersDetail;

    private List flowersList;
    private Context context;

    public Flowers(String flowersName, String flowersImageUrl, String flowersCost, String flowersDetail) {
        this.flowersName = flowersName;
        this.flowersImageUrl = flowersImageUrl;
        this.flowersCost = flowersCost;
        this.flowersDetail = flowersDetail;
    }

    public String getFlowersDetail() {
        return flowersDetail;
    }

    public void setFlowersDetail(String flowersDetail) {
        this.flowersDetail = flowersDetail;
    }

    public String getFlowersName() {
        return flowersName;
    }

    public void setFlowersName(String flowersName) {
        this.flowersName = flowersName;
    }

    public String getFlowersImageUrl() {
        return flowersImageUrl;
    }

    public void setFlowersImageUrl(String flowersImageUrl) {
        this.flowersImageUrl = flowersImageUrl;
    }

    public String getFlowersCost() {
        return flowersCost;
    }

    public void setFlowersCost(String flowersCost) {
        this.flowersCost = flowersCost;
    }
}
