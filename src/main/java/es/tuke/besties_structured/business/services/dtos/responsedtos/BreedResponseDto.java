package es.tuke.besties_structured.business.services.dtos.responsedtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BreedResponseDto {
    
    private Long id;
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

    private Set<String>mascotas;
}
