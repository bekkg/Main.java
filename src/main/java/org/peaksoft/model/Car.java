package org.peaksoft.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Car {
    private Long id;
    private String model;
    private LocalDate yearOfRelease;
    private String color;

    public Car() {

    }

    public Car(String model, LocalDate yearOfRelease, String color) {
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.color = color;
    }

}
