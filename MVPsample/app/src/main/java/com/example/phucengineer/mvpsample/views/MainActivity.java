package com.example.phucengineer.mvpsample.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phucengineer.mvpsample.R;
import com.example.phucengineer.mvpsample.models.Quote;
import com.example.phucengineer.mvpsample.presenters.GetQuoteTask;
import com.example.phucengineer.mvpsample.presenters.MainActivityPresenter;
import com.example.phucengineer.mvpsample.presenters.MainContract;

/**
 * MainActivity is a View, it's only responsible for displaying UI
 */
public class MainActivity extends AppCompatActivity implements MainContract.MainActivityListener, View.OnClickListener {
    private TextView textView;
    private Button button;
    private ProgressBar progressBar;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);

        /*
          this presenter instance is used to invoke methods from MainActivityPresenter:
          onFinish(), onNextButtonClick()
         */
        presenter = new MainActivityPresenter();

        //attach listeners for this View (MainActivity) listener object will be used in presenter
        presenter.setListener(this, new GetQuoteTask());
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setQuote(Quote quote) {
        textView.setText(quote.getQuote());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            presenter.onButtonNextClick();
        }
    }
}
