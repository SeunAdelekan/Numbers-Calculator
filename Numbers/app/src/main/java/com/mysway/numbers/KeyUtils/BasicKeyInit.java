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
 * Created by Iyanu on 09/03/2006.
 */
public class BasicKeyInit {

    boolean evalFlag = false;
    public String evalStr = "";

    View layout;
    TextView previous;
    TextView current;
    EvaluationCore core = new EvaluationCore();

    public BasicKeyInit(View layout,TextView textView,TextView textViewPrev){
        this.layout = layout;
        this.current = textView;
        this.previous = textViewPrev;
    }

    public void initKeys(ArrayList<Fragment> fragList){
        Button btn = (Button) layout.findViewById((R.id.key_seven));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_eight));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_nine));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_slash));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_four));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_five));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_six));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_times));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_clear));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_one));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_two));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_three));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_minus));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_dot));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_zero));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_equals));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

        btn = (Button) layout.findViewById((R.id.key_plus));
        btn.setOnClickListener(returnListener(btn.getId(),fragList));

    }

    private View.OnClickListener returnListener(final int key, final ArrayList<Fragment> fragList){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(resolveKeyValue(key).equals("=")){
                    try {
                        String evaluated = core.evaluate(evalStr);
                        evalStr = evaluated;
                        previous.setText(evalStr);
                        current.setText("");
                        ((TextView)fragList.get(0).getView().findViewById(R.id.text_previous)).setText(evalStr);
                        ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText("");
                        evalFlag = true;
                    } catch (EvaluationException e) {
                        e.printStackTrace();
                    }
                }
                else if(resolveKeyValue(key).equals("C")){
                    evalStr = "";
                    current.setText("0");
                    ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText("0");
                }
                else if(resolveKeyValue(key).equals("*")){
                    evalStr += "*";
                    current.setText(evalStr);
                    ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("/")){
                    evalStr += "/";
                    current.setText(evalStr);
                    ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("+")){
                    evalStr += "+";
                    current.setText(evalStr);
                    ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                    evalFlag = false;
                }
                else if(resolveKeyValue(key).equals("-")){
                    evalStr += "-";
                    current.setText(evalStr);
                    ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                    evalFlag = false;
                }
                else {
                    if(!evalFlag){
                        evalStr += resolveKeyValue(key);
                        current.setText(evalStr);
                        ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                    }else {
                        evalStr = "";
                        evalStr += resolveKeyValue(key);
                        current.setText(evalStr);
                        ((TextView)fragList.get(0).getView().findViewById(R.id.text_current)).setText(evalStr);
                        evalFlag = false;
                    }
                }
            }
        };
    }


    private String resolveKeyValue(int keyId){
        switch (keyId){
            case R.id.key_seven:{
                return "7";
            }
            case R.id.key_eight:{
                return "8";
            }
            case R.id.key_nine:{
                return "9";
            }
            case R.id.key_slash:{
                return "/";
            }
            case R.id.key_four:{
                return "4";
            }
            case R.id.key_five:{
                return "5";
            }
            case R.id.key_six:{
                return "6";
            }
            case R.id.key_times:{
                return "*";
            }
            case R.id.key_clear:{
                return "C";
            }
            case R.id.key_one:{
                return "1";
            }
            case R.id.key_two:{
                return "2";
            }
            case R.id.key_three:{
                return "3";
            }
            case R.id.key_minus:{
                return "-";
            }
            case R.id.key_dot:{
                return ".";
            }
            case R.id.key_zero:{
                return "0";
            }
            case R.id.key_equals:{
                return "=";
            }
            case R.id.key_plus:{
                return "+";
            }
            default: return "Null";
        }
    }
}
