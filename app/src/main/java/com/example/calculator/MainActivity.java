package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static ArrayList<Character> symb = new ArrayList<Character>();
    private String zn = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symb.add('+');
        symb.add('-');
        symb.add('*');
        symb.add('/');
        symb.add('t');
    }



    public void write (View view){
        TextView tv = (TextView)findViewById(R.id.text);
        String txt = tv.getText().toString();
        if(txt.equals("ERROR")){
            txt = "0";
        }
        AppCompatButton but = (AppCompatButton)findViewById(view.getId());
        String str = but.getText().toString();
        switch(str){
            case "+":
            case "-":
            case "*":
            case "/":
                if(zn.equals("")){
                    zn = str;
                }
                else{
                    txt = Calc(zn, txt);
                    zn = str;
                }
            case "sqrt":
                if(txt.length() > 1 && symb.contains(txt.charAt(txt.length() - 2))){
                    if(txt.charAt(txt.length() - 2) == 't'){
                        txt = txt.substring(0, txt.length() - 6);
                    }
                    else{
                        txt = txt.substring(0, txt.length() - 3);
                    }
                }
                txt += " " + str + " ";
                if(str.equals("sqrt")){
                    if(!zn.equals("")) {
                        txt = Calc(zn, txt);
                    }
                    txt = Calc("sqrt", txt);
                    zn = "";
                }
                break;
            case "+/-":
                if(txt.length() > 1 || (txt.length() == 1 && txt.charAt(0) != '0')){
                    String[] words = txt.split(" ");
                    String word;
                    word = words[words.length - 1];
                    if(!symb.contains(word.charAt(word.length() - 1)) && !word.equals("")){
                        txt = txt.substring(0, txt.length() - word.length());
                        if(word.charAt(word.length() - 1) == ')'){
                            word = word.substring(2, word.length() - 1);
                            txt += word;
                        }
                        else{
                            txt += "(-" + word + ")";
                        }
                    }
                }
                break;
            case "1/x":
                txt += " 1/x";
                txt = Calc("1/x", txt);
                zn = "";
                /*
                txt += "^(-1)";
                */
                break;
            case "C":
                txt = "0";
                break;
            case "=":
                if(!zn.equals("")) {
                    txt = Calc(zn, txt);
                    zn = "";
                }
                /*
                String[] words = txt.split(" ");
                double res = 0;
                for (int i = 0; i < words.length; i++) {
                    switch (words[i]) {
                        case "+":

                            break;
                        case "-":
                            break;
                        case "*":
                            break;
                        case "/":
                            break;
                        case "sqrt":
                            break;
                        default:

                            break;
                    }
                }*/
                break;
            case ".":
                txt += str;
                break;
            default:
                if(txt.length() == 1 && txt.charAt(0) == '0'){
                    txt = "";
                }
                txt += str;
                break;
        }
        tv.setText(txt);
    }

    private String Calc(String znak, String txt){
        String[] words = txt.split(" ");
        String word;
        double res = 0;
        for (int i = 0; i < words.length; i++) {
            word = words[i];
            switch (word) {
                case "+":
                    word = words[i + 1];
                    if(word.charAt(0) == '('){
                        word = word.substring(1, word.length() - 1);
                    }
                    try {
                        res += Double.parseDouble(word);
                    }
                    catch(Exception e) {
                        return "ERROR";
                    }
                    if(res % 1 == 0){
                        return (int)res + "";
                    }
                    else{
                        return res + "";
                    }
                case "-":
                    word = words[i + 1];
                    if(word.charAt(0) == '('){
                        word = word.substring(1, word.length() - 1);
                    }
                    try {
                        res -= Double.parseDouble(word);
                    }
                    catch(Exception e) {
                        return "ERROR";
                    }
                    if(res % 1 == 0){
                        return (int)res + "";
                    }
                    else{
                        return res + "";
                    }
                case "*":
                    word = words[i + 1];
                    if(word.charAt(0) == '('){
                        word = word.substring(1, word.length() - 1);
                    }
                    try {
                        res *= Double.parseDouble(word);
                    }
                    catch(Exception e) {
                        return "ERROR";
                    }
                    if(res % 1 == 0){
                        return (int)res + "";
                    }
                    else{
                        return res + "";
                    }
                case "/":
                    word = words[i + 1];
                    if(word.charAt(0) == '('){
                        word = word.substring(1, word.length() - 1);
                    }
                    try {
                        res /= Double.parseDouble(word);
                    }
                    catch(Exception e) {
                        return "ERROR";
                    }
                    if(res % 1 == 0){
                        return (int)res + "";
                    }
                    else{
                        return res + "";
                    }
                case "sqrt":
                    res = Math.sqrt(res);
                    return res + "";
                case "1/x":
                    res = 1/res;
                    if(res % 1 == 0){
                        return (int)res + "";
                    }
                    else{
                        return res + "";
                    }
                default:
                    if(word.charAt(0) == '('){
                        word = word.substring(1, word.length() - 1);
                    }
                    try {
                        res = Double.parseDouble(word);
                    }
                    catch(Exception e) {
                        return "ERROR";
                    }
                    break;
            }
        }
        return txt;
    }
}