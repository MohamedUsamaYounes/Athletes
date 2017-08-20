package com.apptcom.athletes.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apptcom.athletes.R;
import com.apptcom.athletes.model.Athlete;
import com.apptcom.athletes.presnter.IAthletesPresenter;
import com.apptcom.athletes.presnter.IAthletesView;
import com.apptcom.athletes.presnter.Presenter;
import com.apptcom.athletes.utils.InternetConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IAthletesView {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.athlete_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeToRefresh;

    private AllAthletesAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    IAthletesPresenter iAthletesPresenter;
    private ArrayList<Athlete> allAthletesList;
    private int pageNum = 2;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        registerForContextMenu(mRecyclerView);

        // set refresh listener
        mSwipeToRefresh.setOnRefreshListener(this);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        iAthletesPresenter = new Presenter(this);
        allAthletesList = new ArrayList<>();


        if (InternetConnection.isNetworkAvailable(this)) {
            // load data
            mProgressBar.setVisibility(View.VISIBLE);
            iAthletesPresenter.loadAthletesFromServer();
        } else {
            Toast.makeText(this, "there is no internet connection", Toast.LENGTH_SHORT).show();
        }

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                iAthletesPresenter.loadAthletesFromServer();
                pageNum++;
            }
        };

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }


    public void setViewsVisibility() {
        mProgressBar.setVisibility(View.GONE);
        if (mSwipeToRefresh.isRefreshing()) {
            mSwipeToRefresh.setRefreshing(false);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        if (InternetConnection.isNetworkAvailable(this)) {
            allAthletesList.clear();
            // overwrite list with empty one
            scrollListener.resetState();
            iAthletesPresenter.loadAthletesFromServer();
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void setAllAthletesView(ArrayList<Athlete> AthletesArrayList) {
        if (allAthletesList.size() > 0) {
            allAthletesList.addAll(AthletesArrayList);
            // notify adapter that data has been changed
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), allAthletesList.size() - 1);
                }
            });
        } else {
            // setting recycler
            allAthletesList = AthletesArrayList;
            mAdapter = new AllAthletesAdapter(AthletesArrayList, MainActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void loadFailure() {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Couldn't load data", Toast.LENGTH_SHORT).show();
    }
}
