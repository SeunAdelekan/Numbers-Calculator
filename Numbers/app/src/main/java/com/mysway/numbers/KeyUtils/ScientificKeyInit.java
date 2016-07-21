package com.mysway.numbers.KeyUtils;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mysway.numbers.CoreProcessor.EvaluationCore;
import com.mysway.numbers.R;

import net.sourceforge.jeval.EvaluationException;

import java.util.ArrayList;

/**
 * Created by Iyanu on 19/03/2016.
 */
public class ScientificKeyInit {

    int valuesEvaluated = 0;
    boolean evalFlag = false;
    public String evalStr = "";
    private ArrayList<Fragment> fragList;

    View layout;
    TextView previous;
    TextView current;
    EvaluationCore core = new EvaluationCore();

    public ScientificKeyInit(View layout,TextView textView,TextView textViewPrev,ArrayList fragArrayList){
        this.layout = layout;
        this.current = textView;
        this.previous = textViewPrev;
        this.fragList = fragArrayList;
    }

    public void initKeys(){
        Button btn = (Button) layout.findViewById((R.id.key_sin));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_cos));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_tan));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_log));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_ln));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_e));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_pi));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_factorial));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_sqrt));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_cbrt));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_square));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_power));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_lPar));
        btn.setOnClickListener(returnListener(btn.getId()));

        btn = (Button) layout.findViewById((R.id.key_rPar));
        btn.setOnClickListener(returnListener(btn.getId()));

    }

    private View.OnClickListener returnListener(final int key){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(resolveKeyValue(key).equals("=")){
                    try {
                        String evaluated = core.evaluate(evalStr);
                        evalStr = evaluated;
                        previous.setText(evalStr);
                        current.setText("");
                        evalFlag = true;

                        TextView textView = (TextView)fragList.get(1).getView().findViewById(R.id.text_previous);
                        textView.setText(evalStr);
                    } catch (EvaluationException e) {
                        e.printStackTrace();
                    }
                }
                else if(resolveKeyValue(key).equals("C")){
                    evalStr = "";
                    current.setText("0");
                }
                else if(resolveKeyValue(key).equals("*")){
                    evalStr += "*";
                    current.setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("/")){
                    evalStr += "/";
                    current.setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("+")){
                    evalStr += "+";
                    current.setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("-")){
                    evalStr += "-";
                    current.setText(evalStr);
                    evalFlag = false;
                }
                else {
                    if(!evalFlag){
                        evalStr += resolveKeyValue(key);
                        current.setText(evalStr);
                    }else {
                        evalStr = "";
                        evalStr += resolveKeyValue(key);
                        current.setText(evalStr);
                        evalFlag = false;
                    }
                }
            }
        };
    }

    private String resolveKeyValue(int keyId){
        switch (keyId){
            case R.id.key_sin:{
                return "sin(";
            }
            case R.id.key_cos:{
                return "cos(";
            }
            case R.id.key_tan:{
                return "tan(";
            }
            case R.id.key_log:{
                return "log(";
            }
            case R.id.key_ln:{
                return "ln(";
            }
            case R.id.key_e:{
                return "e";
            }
            case R.id.key_pi:{
                return "6";
            }
            case R.id.key_factorial:{
                return "*";
            }
            case R.id.key_sqrt:{
                return "C";
            }
            case R.id.key_cbrt:{
                return "1";
            }
            case R.id.key_square:{
                return "2";
            }
            case R.id.key_power:{
                return "3";
            }
            case R.id.key_lPar:{
                return "-";
            }
            case R.id.key_rPar:{
                return ".";
            }
            default: return "Null";
        }
    }
}
