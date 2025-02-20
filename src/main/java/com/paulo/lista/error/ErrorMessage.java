package com.paulo.lista.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private int status;
    private String message;
    private LocalDateTime timeStamp;
    private List<String> errors;

    /*
        Construtor
     */
    public ErrorMessage(int value, String message, LocalDateTime now) {
        this.status = value;
        this.message = message;
        this.timeStamp = now;
    }

}
