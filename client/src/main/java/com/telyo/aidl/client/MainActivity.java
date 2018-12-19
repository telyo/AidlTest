package com.telyo.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.telyo.aidl.server.People;
import com.telyo.aidl.server.PeopleController;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBind;
    private Button btnUnbind;
    private Button btnGetDate;
    private TextView tvState;
    private TextView tvDates;

    private PeopleController peopleController;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            peopleController = PeopleController.Stub.asInterface(service);
            btnGetDate.setEnabled(true);
            btnAddPeople.setEnabled(true);
            tvState.setText("binded");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            btnGetDate.setEnabled(false);
            btnAddPeople.setEnabled(false);
            tvState.setText("unbinded");
            peopleController = null;
        }
    };
    private Button btnAddPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);
        btnGetDate = findViewById(R.id.btnGetDate);
        btnAddPeople = findViewById(R.id.btnAddPeople);
        tvState = findViewById(R.id.tvState);
        tvDates = findViewById(R.id.tvDates);

        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnGetDate.setOnClickListener(this);
        btnAddPeople.setOnClickListener(this);
        btnGetDate.setEnabled(false);
        btnAddPeople.setEnabled(false);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBind:
                Intent intent = new Intent();
                intent.setPackage("com.telyo.aidl.server");
                intent.setAction("com.telyo.server.action");
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                tvState.setText("binding");

                break;
            case R.id.btnUnbind:
                unbindService(connection);
                tvState.setText("unbinding");
                break;
            case R.id.btnGetDate:
                if (peopleController != null) {
                    try {
                        List<People> peoples = peopleController.getPeople();
                        StringBuilder peopleDetails = new StringBuilder();
                        for (People people:peoples){
                            peopleDetails.append(people.getName() + "  " + people.getAge() + "\n");
                        }
                        tvDates.setText(peopleDetails.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.btnAddPeople:
                if (peopleController != null) {
                    try {
                        peopleController.addPeople(new People("李四","23"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        tvState.setText("unbinding");
        super.onDestroy();
    }
}
