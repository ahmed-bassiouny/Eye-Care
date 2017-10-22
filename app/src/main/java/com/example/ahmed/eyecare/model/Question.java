package com.example.ahmed.eyecare.model;

import java.util.List;

/**
 * Created by ahmed on 15/10/17.
 */

public class Question {

    private String activated;
    public String question;
    public List<Answer> answers;

    public boolean getActivated(){
        if(activated!=null &&activated.equals("1"))
            return true;
        return false;
    }
}
