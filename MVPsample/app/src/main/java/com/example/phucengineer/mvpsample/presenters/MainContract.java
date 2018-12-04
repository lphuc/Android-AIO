package com.example.phucengineer.mvpsample.presenters;

import com.example.phucengineer.mvpsample.models.Quote;

/**
 * Hold all contracts between View and Presenter
 */
public interface MainContract {

    /**
     * this contract will be implemented by the presenter to sure it will implement these 2 methods on it's own way
     * the methods will be triggered from MainActivity by using a PresenterListener instance
     */
    interface PresenterListener {
        void onButtonNextClick();
        void onDestroy();
    }

    /**
     * This contract will be implemented by MainActivity to sure it will implement these 3 methods on it's own way
     * the methods will be triggered from Presenter by using a MainActivityListener instance
     */
    interface MainActivityListener {
        void showProgress();

        void hideProgress();

        void setQuote(Quote quote);
    }

    /**
     * This contract will be implemented by GetQuoteTask to sure it will implement the method getNextQuote on it's own way
     * the method getNextQuote will be triggered from Presenter by using a GetQuoteListener instance
     */
    interface GetQuoteListener {
        // This contract will be implemented by the Presenter then it will use to call setQuote() to update UI in MainActivity
        interface OnFinishListener {

            // This callback is triggered from GetQuoteTask to notify the MainActivityPresenter when it's done the task getNextQuote()
            void onFinished(Quote quote);
        }

        void getNextQuote(OnFinishListener listener);
    }

}
