package com.example.phucengineer.mvpsample.presenters;

import com.example.phucengineer.mvpsample.models.Quote;

/**
 * This is the presenter which will handle all logic and update the UI
 */
public class MainActivityPresenter implements MainContract.GetQuoteListener.OnFinishListener, MainContract.PresenterListener {

    private MainContract.MainActivityListener mainActivityListener;
    private MainContract.GetQuoteListener getQuoteListener;

    /**
     * @param activityListener -> used to trigger method showProgress(), hideProgress(), setQuote() to update UI in MainActivity
     * @param getQuoteListener -> use to trigger method getNextQuote() in GetQuoteTask whenever the button is clicked
     */
    public void setListener(MainContract.MainActivityListener activityListener, MainContract.GetQuoteListener getQuoteListener) {
        this.mainActivityListener = activityListener;
        this.getQuoteListener = getQuoteListener;
    }

    /**
     * this callback function is triggered when the getNextQuote() is done in GetQuoteTask and return here a new quote
     *
     * @param quote
     */
    @Override
    public void onFinished(Quote quote) {
        if (mainActivityListener != null) {
            mainActivityListener.setQuote(quote);
            mainActivityListener.hideProgress();
        }
    }

    /**
     * This method will be triggered whenever the button from View is clicked
     */
    @Override
    public void onButtonNextClick() {
        mainActivityListener.showProgress();
        if (getQuoteListener != null) {
            getQuoteListener.getNextQuote(this);
        }
    }

    /**
     * Destroy all listener instances when needed
     */
    @Override
    public void onDestroy() {
        mainActivityListener = null;
        getQuoteListener = null;
    }
}
