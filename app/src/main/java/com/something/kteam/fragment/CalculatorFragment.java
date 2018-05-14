package com.something.kteam.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private View root;
    private Button mZero, mOne , mTwo , mThree,
            mFour, mFive, mSix, mSeven,
            mEight, mNine;
    private Button mDot, mResult, mAdd, mSub,
            mMul, mDiv, mPercent,
            mFraction, mAc;
    private TextView mDisplayResult;
    String display;
    private CallBack mCallBack;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (CallBack) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) container.getParent();
        root = inflater.inflate(R.layout.fragment_calculator, container,false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapping();
        Bundle bundle = getArguments();
        if(bundle != null){
            String data = bundle.getString("result");
                mDisplayResult.setText(data);
        }

        onClick();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    private void mapping() {
        mZero = root.findViewById(R.id.button_zero);
        mOne = root.findViewById(R.id.button_one);
        mTwo = root.findViewById(R.id.button_two);
        mThree = root.findViewById(R.id.button_three);
        mFour = root.findViewById(R.id.button_four);
        mFive = root.findViewById(R.id.button_five);
        mSix = root.findViewById(R.id.button_six);
        mSeven = root.findViewById(R.id.button_seven);
        mEight = root.findViewById(R.id.button_eight);
        mNine = root.findViewById(R.id.button_nine);
        mDot = root.findViewById(R.id.button_dot);
        mResult =root.findViewById(R.id.button_result);
        mAdd = root.findViewById(R.id.button_add);
        mSub = root.findViewById(R.id.button_sub);
        mMul = root.findViewById(R.id.button_mul);
        mDiv = root.findViewById(R.id.button_div);
        mPercent = root.findViewById(R.id.button_percent);
        mFraction = root.findViewById(R.id.button_fraction);
        mDisplayResult = root.findViewById(R.id.text_result);
        mAc = root.findViewById(R.id.button_ac);
        display = "";
    }
    private void onClick(){
        mZero.setOnClickListener(this);
        mOne.setOnClickListener(this);
        mTwo.setOnClickListener(this);
        mThree.setOnClickListener(this);
        mFour.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mSix.setOnClickListener(this);
        mSeven.setOnClickListener(this);
        mEight.setOnClickListener(this);
        mNine.setOnClickListener(this);
        mDot.setOnClickListener(this);
        mResult.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mSub.setOnClickListener(this);
        mMul.setOnClickListener(this);
        mDiv.setOnClickListener(this);
        mPercent.setOnClickListener(this);
        mFraction.setOnClickListener(this);
        mAc.setOnClickListener(this);
    }
    private void calculate(String s){
        ArrayList<String> mString = new ArrayList<>();
        ArrayList<String> mNumber = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == '*' || s.charAt(i) == '/'
                    || s.charAt(i) == '%') {
                mString.add(s.substring(i, i + 1));
            }
        }
        StringTokenizer st = new StringTokenizer(s, "+-*/%");
        while(st.hasMoreTokens()){
            mNumber.add(st.nextToken());
        }
//        if(!Check.Check(s)){
//            Toast.makeText(MainActivity.this,"Loi phep toan",Toast.LENGTH_SHORT).show();
//            edt.setText("");
//            return;
//        }
        if(mString.size()>mNumber.size()){
            mDisplayResult.setText(getResources().getString(R.string.error));
            return;
        }
        for(int i = 0 ;i < mString.size() ; i++){
            if(mString.get(i).equals("*")){
                Object t = Float.parseFloat(mNumber.get(i)) * Float.parseFloat(mNumber.get(i+1));
                mString.remove(i);
                mNumber.set(i+1,  t.toString());
                mNumber.remove(i);
                i--;

            }else
            if(mString.get(i).equals("/")){
                Object t = Float.parseFloat(mNumber.get(i)) / Float.parseFloat(mNumber.get(i+1));
                mString.remove(i);
                mNumber.set(i+1,  t.toString());
                mNumber.remove(i);
                i--;
            }else if(mString.get(i).equals("%")) {
                Object t = Float.parseFloat(mNumber.get(i)) % Float.parseFloat(mNumber.get(i+1));
                System.out.println(Float.parseFloat(t.toString()));
                mString.remove(i);
                mNumber.set(i+1,  t.toString());
                mNumber.remove(i);
                i--;
            }
        }
        for(int i = 0;i <mString.size();i ++){
            if(mString.get(i).equals("+")){
                Object t = Float.parseFloat(mNumber.get(i)) + Float.parseFloat(mNumber.get(i+1));
                mString.remove(i);
                mNumber.set(i+1,  t.toString());
                mNumber.remove(i);
                i--;

            }else
            if(mString.get(i).equals("-")){
                Object t = Float.parseFloat(mNumber.get(i)) - Float.parseFloat(mNumber.get(i+1));
                mString.remove(i);
                mNumber.set(i+1,  t.toString());
                mNumber.remove(i);
                i--;
            }
        }
        display = mNumber.get(0);
        mDisplayResult.setText(display);
    }

    @Override
    public void onClick(View view) {
        int id  = view.getId();
        switch (id){
            case R.id.button_zero:
                display += "0";
                mDisplayResult.setText(display);
                break;
            case R.id.button_one:
                display += "1";
                mDisplayResult.setText(display);
                break;
            case R.id.button_two:
                display += "2";
                mDisplayResult.setText(display);
                break;
            case R.id.button_three:
                display += "3";
                mDisplayResult.setText(display);
                break;
            case R.id.button_four:
                display += "4";
                mDisplayResult.setText(display);
                break;
            case R.id.button_five:
                display += "5";
                mDisplayResult.setText(display);
                break;
            case R.id.button_six:
                display += "6";
                mDisplayResult.setText(display);
                break;
            case R.id.button_seven:
                display += "7";
                mDisplayResult.setText(display);
                break;
            case R.id.button_eight:
                display += "8";
                mDisplayResult.setText(display);
                break;
            case R.id.button_nine:
                display += "9";
                mDisplayResult.setText(display);
                break;
            case R.id.button_dot:
                display += ".";
                mDisplayResult.setText(display);
                break;
            case R.id.button_result:
                calculate(display);
                mDisplayResult.setText(splitString(display));
                mCallBack.onData(splitString(display));
                break;
            case R.id.button_add:
                display += "+";
                mDisplayResult.setText(display);
                break;
            case R.id.button_sub:
                display += "-";
                mDisplayResult.setText(display);
                break;
            case R.id.button_div:
                display += "/";
                mDisplayResult.setText(display);
                break;
            case R.id.button_mul:
                display += "*";
                mDisplayResult.setText(display);
                break;
            case R.id.button_percent:
                display += "%";
                mDisplayResult.setText(display);
                break;
            case R.id.button_fraction:
                break;
            case R.id.button_ac:
                if(display.length() > 0){
                    display = display.substring(0,display.length()-1);
                    mDisplayResult.setText(display);
                }
                break;
        }
    }
    public String splitString(String mS){
        int mPosition = 0;
        for(int i = 0 ; i < mS.length() ; i ++){
            if(mS.charAt(i)=='.'){
                mPosition = i;
                break;
            }
        }
        return (mS.substring(mPosition+1).length() > 1)? mS : mS.substring(0,mPosition);
    }

    public interface CallBack{
       void onData(String result);
    }
}
