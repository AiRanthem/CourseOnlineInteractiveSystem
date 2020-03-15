package com.gp03d.learn.springboot.common;

public class ErrorText {
    String text;

    public ErrorText(String text, String continueBtn) {
        this.text = text;
        this.continueBtn = continueBtn;
    }

    public String getContinueBtn() {
        return continueBtn;
    }

    public void setContinueBtn(String continueBtn) {
        this.continueBtn = continueBtn;
    }

    String continueBtn;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
