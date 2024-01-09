package io.innodev.blogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private Integer postId;
    private String postTitle;
    private String content;
    private String image;
    private Date addedDate;
    private transient CategoryDTO category;
    private transient UserDTO user;
    private transient Set<CommentDTO> comments = new HashSet<>();
}
