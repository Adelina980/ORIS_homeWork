package org.example.model;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Profession {
    private Long id;
    private String name;

    public Profession() {}

    public Profession(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
