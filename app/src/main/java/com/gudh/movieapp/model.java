package com.gudh.movieapp;

public class model {
    String original_title,vote_average;

    public model(String original_title, String vote_average) {
        this.original_title = original_title;
        this.vote_average = vote_average;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
