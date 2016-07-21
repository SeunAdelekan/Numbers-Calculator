package com.mysway.numbers.CoreProcessor;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.util.ArrayList;

/**
 * Created by Iyanu on 19/03/2016.
 */
public class EvaluationCore {

    ArrayList list = new ArrayList();
    int pos = 0;
    int bracketFlag = 0;
    String evalString;


    public EvaluationCore(){

    }

    private void evaluateString(){
        String numStr = null;

        for (int i = pos; i < evalString.length(); i++){

            switch ((int)peek(evalString, pos++)){
                case 42:{
                    list.add("(");
                    bracketFlag++;
                }
                case 47:{
                    list.add("(");
                    bracketFlag++;
                }
            }

            if((int) evalString.charAt(i) >= 48 && (int) evalString.charAt(i) <= 57){
                numStr += evalString.charAt(i);
            }else{
                list.add(Integer.parseInt(numStr));
            }
            pos++;

            switch ((int)peek(evalString, pos++)){
                case 43:{
                    list.add(")");
                    bracketFlag--;
                }
                case 45:{
                    list.add(")");
                    bracketFlag--;
                }
            }
        }
    }

    public String evaluate(String str) throws EvaluationException {
        Evaluator evaluator = new Evaluator();
        return evaluator.evaluate(str);
    }

    public void add(String str){
        this.evalString = str;
    }

    private int peek(String str, int pos){
        return (int)str.charAt(pos);
    }
}
