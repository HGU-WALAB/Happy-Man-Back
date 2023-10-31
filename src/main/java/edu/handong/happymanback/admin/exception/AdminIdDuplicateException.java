package edu.handong.happymanback.admin.exception;

public class AdminIdDuplicateException extends IllegalStateException{
    public AdminIdDuplicateException() {
        super("직번이 중복됩니다.");
    }
}
