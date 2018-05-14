package com.something.kteam.fragment;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements CalculatorFragment.CallBack {
    private FragmentManager mFragmentManager;
    private SharedPreferences mSharedPreferences;
    private String mResult;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        CalculatorFragment calculatorFragment = new CalculatorFragment();
        transaction.add(R.id.relative_caculator, calculatorFragment);
        transaction.commit();

        mSharedPreferences = getSharedPreferences("caculator", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        String result = mSharedPreferences.getString("result", null);
        if (result != null) {
            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            calculatorFragment.setArguments(bundle);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_clear:
                mEditor.clear();
                mEditor.apply();
                break;
            case R.id.item_savelastresult:
                mEditor.putString("result", mResult);
                mEditor.apply();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onData(String result) {
        mResult = result;
    }
}
