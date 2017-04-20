package com.linkstreet.vinoth.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linkstreet.vinoth.myapplication.db.DaoSession;
import com.linkstreet.vinoth.myapplication.db.User;
import com.linkstreet.vinoth.myapplication.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;
    private EditText firstname;
    private EditText lastname;
    private EditText mobile;
    private EditText email;
    private EditText userid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoSession daoSession = ((AppController) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        mobile = (EditText) findViewById(R.id.mobile_number);
        email = (EditText) findViewById(R.id.email);
        userid = (EditText) findViewById(R.id.user_id);
        Button create = (Button) findViewById(R.id.new_user);
        Button listUser = (Button) findViewById(R.id.list_users);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        listUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(i);
            }
        });

    }

    private void addUser() {
        int userId = Integer.parseInt(userid.getText().toString());
        long mobileNo = Long.parseLong(mobile.getText().toString());
        User user = new User();
        user.setLast_name(lastname.getText().toString());
        user.setFirst_name(firstname.getText().toString());
        user.setEmail(email.getText().toString());
        user.setUser_id(userId);
        user.setMobile_number(mobileNo);
        userDao.insert(user);
        Log.d("DaoExample", "Inserted new note, ID: " + user.getId());
        //retrieving done
        ArrayList<Long> userIds = new ArrayList<Long>();
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.First_name.eq("deepika")).list();
        for(User test: userList) {
            userIds.add(test.getId());
            Log.d("User Data",test.getId() + " " + test.getUser_id() + " " +test.getFirst_name() + " " + test.getLast_name() + " " + test.getEmail()+" "+ test.getMobile_number());
        }
        //deletion example
        for (Long num : userIds) {
            userDao.deleteByKey(num);
        }
        Toast.makeText(this, "User Inserted Successfully:" + user.getId(), Toast.LENGTH_SHORT).show();
    }
}
