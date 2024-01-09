package io.innodev.blogapp.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String messages;
    private boolean success;
    private String date;
}
