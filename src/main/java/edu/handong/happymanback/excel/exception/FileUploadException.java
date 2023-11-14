package edu.handong.happymanback.excel.exception;

import java.io.IOException;

public class FileUploadException extends IOException {
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
