package org.warpy.domain.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import enums.Status;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "testitem")
@Getter
@Setter
public class TestItem {

    @Id
    @GeneratedValue
    public Long id;

    private UUID guid;

    private String name;

    private Status status;
}
