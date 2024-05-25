package es.tuke.besties_structured.business.services.dtos.responsedtos;

import java.util.List;
import lombok.Data;

@Data
public class TypeResponseDto {

    private Long id;
    private String clase;
    private String descripcion;
    private List<String>pets;  
    
}
