package io.innodev.blogapp.config;

import io.innodev.blogapp.exceptions.ResourceNotFoundException;

public class Constant {

    private Constant() {
        throw new ResourceNotFoundException("Value", "value", 1L);
    }

    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY = "postId";
    public static final String SORT_ORDER = "asc";
}
