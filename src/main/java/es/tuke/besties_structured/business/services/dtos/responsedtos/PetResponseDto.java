package es.tuke.besties_structured.business.services.dtos.responsedtos;

import es.tuke.besties_structured.business.persistence.SexoEnum;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PetResponseDto {

    private Long id;
    private String nombre;
    private Date fecha_nacimiento;
    //private String edad;
    private boolean active;
    private SexoEnum sexo;
    private String tipo;
    private Set<String>razas;

    
}
