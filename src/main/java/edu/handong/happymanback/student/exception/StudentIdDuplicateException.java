package edu.handong.happymanback.student.exception;

public class StudentIdDuplicateException extends IllegalStateException{
    public StudentIdDuplicateException() {
        super("학번이 중복됩니다.");
    }
}