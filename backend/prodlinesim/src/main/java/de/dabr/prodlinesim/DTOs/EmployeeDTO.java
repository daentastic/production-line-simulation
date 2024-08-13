package de.dabr.prodlinesim.DTOs;

import de.dabr.prodlinesim.model.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private Station station;

}