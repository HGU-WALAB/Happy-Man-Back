package edu.handong.happymanback.user.exception;

public class PersonalIdDuplicateException extends IllegalStateException{
    public PersonalIdDuplicateException() {
        super("학번이 중복됩니다.");
    }
}