package com.baibei.myservicespace.dto;

import java.util.ArrayList;
import java.util.List;

public class FiltersCard {

    private String minPrice;
    private String maxPrice;
    private String[] categories;
    private boolean TGBot = false;
    private boolean Website = false;
    private boolean Backend = false;
    private boolean Other = false;

    public FiltersCard() {}

    public double getMinPrice() {
        try {
            return Double.parseDouble(minPrice);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = String.valueOf(minPrice);
    }

    public double getMaxPrice() {
        try {
            return Double.parseDouble(maxPrice);
        } catch (Exception e) {
            return 9999.0;
        }
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = String.valueOf(maxPrice);
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public boolean isTGBot() {
        return TGBot;
    }

    public void setTGBot(boolean TGBot) {
        this.TGBot = TGBot;
    }

    public boolean isWebsite() {
        return Website;
    }

    public void setWebsite(boolean Website) {
        this.Website = Website;
    }

    public boolean isBackend() {
        return Backend;
    }

    public void setBackend(boolean Backend) {
        this.Backend = Backend;
    }

    public boolean isOther() {
        return Other;
    }

    public void setOther(boolean Other) {
        this.Other = Other;
    }

    public void make() {
        double min = (minPrice != null && !minPrice.isEmpty()) ? Double.parseDouble(minPrice) : 0.0;
        double max = (maxPrice != null && !maxPrice.isEmpty()) ? Double.parseDouble(maxPrice) : 9999.0;

        if (min > max) min = max;

        this.minPrice = String.valueOf(min);
        this.maxPrice = String.valueOf(max);

        List<String> selectedCategories = new ArrayList<>();
        if (TGBot) selectedCategories.add("TGBOT");
        if (Website) selectedCategories.add("WEBSITE");
        if (Backend) selectedCategories.add("BACKEND");
        if (Other) selectedCategories.add("OTHER");

        categories = selectedCategories.toArray(new String[0]);
    }
}
