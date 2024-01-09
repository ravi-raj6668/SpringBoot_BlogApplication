package io.innodev.blogapp.payloads;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PostResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private transient List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private Long totalEle;
    private int totalPages;
    private boolean lastPage;


}
