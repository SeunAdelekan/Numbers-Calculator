package com.mysway.numbers.CoreProcessor;

import java.util.ArrayList;

/**
 * Created by Iyanu on 24/05/2016.
 */
public class ScriptProcessor {
    ScriptProcessor(){
        this.word = "add";
        keywords.add(this.word);
        this.word = "sub";
        keywords.add(this.word);
        this.word = "div";
        keywords.add(this.word);
        this.word = "mul";
        keywords.add(this.word);
        this.word = "sqrt";
        keywords.add(this.word);
        this.word = "cbrt";
        keywords.add(this.word);
        this.word = "sq";
        keywords.add(this.word);
        this.word = "pow";
        keywords.add(this.word);
    }
    private String word;
    private ArrayList<String> keywords = new ArrayList<>();


    /*public String evaluate(String scriptStr){
        String[] strArray = scriptStr.split(" ");
    }*/
}
