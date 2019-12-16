package com.firat.Islem;

/**
 * Created by FiratUlgay on 15.12.2019.
 */
public class CustomException extends Exception {

    String hataMesaji;

    public CustomException(String hataMesaji){
        this.hataMesaji = hataMesaji;
    }

    @Override
    public String getMessage() {
        return hataMesaji;
    }
}
