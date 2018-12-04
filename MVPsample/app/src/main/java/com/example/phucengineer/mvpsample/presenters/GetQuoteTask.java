package com.example.phucengineer.mvpsample.presenters;

import com.example.phucengineer.mvpsample.models.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a small presenter which in charge of small task, eg. getting new quote
 * and return to the presenter, and the presenter will then use that quote to update the UI by calling setQuote() method
 */
public class GetQuoteTask implements MainContract.GetQuoteListener {
    private List<Quote> list = new ArrayList<>();

    public GetQuoteTask() {
    }

    private void initModelData() {
        list.add(new Quote("quote 1"));
        list.add(new Quote("quote 2"));
        list.add(new Quote("quote 3"));
        list.add(new Quote("quote 4"));
        list.add(new Quote("quote 5"));
    }

    private Quote getRandomQuote() {
        initModelData();
        Random random = new Random();
        int index = random.nextInt(list.size());

        return list.get(index);
    }

    @Override
    public void getNextQuote(final OnFinishListener listener) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getRandomQuote());
            }
        }, 1000);
    }
}
