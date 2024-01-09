package io.innodev.blogapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "comment")
@Data
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    private transient Post post;
}
