package io.innodev.blogapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Message {
    private String messages;
    private boolean success;
    private String date;
}
