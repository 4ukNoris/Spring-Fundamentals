package com.paintingscollectors.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private long id;
    private String username;
    List<PaintingDto> paintingList;

    public UserDto() {
        this.paintingList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public UserDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<PaintingDto> getPaintingList() {
        return paintingList;
    }

    public UserDto setPaintingList(List<PaintingDto> paintingList) {
        this.paintingList = paintingList;
        return this;
    }
}
