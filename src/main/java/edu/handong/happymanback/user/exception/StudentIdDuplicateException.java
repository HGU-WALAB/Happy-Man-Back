package edu.handong.happymanback.user.exception;

public class StudentIdDuplicateException extends IllegalStateException{
    public StudentIdDuplicateException() {
        super("학번이 중복됩니다.");
    }
}