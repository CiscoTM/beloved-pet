package es.tuke.besties_structured.business.services.dtos.requestdtos;

import lombok.Data;
import java.util.List;

@Data
public class TypeRequestDto {

    private String clase;
    private String descripcion;
    private List<Long>petsIds; 

}
