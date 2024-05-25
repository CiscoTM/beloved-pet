package es.tuke.besties_structured.business.services.dtos.requestdtos;

import lombok.Data;
import java.sql.Date;
import java.util.Set;

import es.tuke.besties_structured.business.persistence.SexoEnum;

@Data
public class PetRequestDto {

    private String nombre;
    private Date fecha_nacimiento;
    private boolean active;
    private SexoEnum sexo;
    private Long tipoId;
    private Set<Long>razasIds;

}
