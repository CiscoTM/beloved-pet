package es.tuke.besties_structured.business.services;

import java.util.List;

import es.tuke.besties_structured.business.persistence.entities.TypeEntity;
import es.tuke.besties_structured.business.services.dtos.requestdtos.TypeRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.TypeResponseDto;

public interface ITypeService {
    
    public TypeEntity getType(Long id);
    public TypeResponseDto save(TypeRequestDto dto);
    public TypeResponseDto getTypeById(Long id);
    public List<TypeResponseDto> getAllTypes();
    public TypeResponseDto delete(Long id);
    
    public TypeResponseDto edit(Long typeId,TypeRequestDto dto);
}
