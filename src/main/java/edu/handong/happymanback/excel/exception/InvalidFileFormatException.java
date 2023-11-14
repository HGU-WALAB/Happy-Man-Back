package edu.handong.happymanback.excel.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String format){super("Please upload an "+format+" file!");}
}
