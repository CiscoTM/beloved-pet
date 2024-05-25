package es.tuke.besties_structured.business.services.dtos.requestdtos;

import lombok.Data;
import java.util.Set;



@Data

public class BreedRequestDto {

    private String nombre;
    private String origen;
    private String longevidad;
    private String color;
    private String pelaje;
    private String tamano;
    private String altura;
    private String peso;
    private String temperamento;
    private String necesidades;

    private Set<Long> petIds;

    
}
