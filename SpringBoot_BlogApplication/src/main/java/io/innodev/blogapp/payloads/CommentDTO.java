package io.innodev.blogapp.payloads;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private int id;
    private String content;
}
