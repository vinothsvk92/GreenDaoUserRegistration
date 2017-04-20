package com.linkstreet.vinoth.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linkstreet.vinoth.myapplication.db.DaoSession;
import com.linkstreet.vinoth.myapplication.db.User;
import com.linkstreet.vinoth.myapplication.db.UserDao;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        DaoSession daoSession = ((AppController) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        List<User> userList = userDao.queryBuilder().list();

        mAdapter = new MyAdapter(userList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
