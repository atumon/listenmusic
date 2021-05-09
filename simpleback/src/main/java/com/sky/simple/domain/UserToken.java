package com.sky.simple.domain;

public class UserToken {

    private Long id;

    private String name;


    public UserToken(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
