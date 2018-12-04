package com.example.phucengineer.recycleviewsample;

/*
 * Created by Phuc Engineer on 9/8/2018.
 */
public class MovieModel {
    private String name;
    private String userRating;
    private String metaScore;

    public MovieModel(String name, String userRating, String metaScore) {
        this.name = name;
        this.userRating = userRating;
        this.metaScore = metaScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(String metaScore) {
        this.metaScore = metaScore;
    }
}
