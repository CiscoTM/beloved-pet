package es.tuke.besties_structured.business.services.implementations;

import org.springframework.stereotype.Service;

import es.tuke.besties_structured.business.services.ITypeService;
import es.tuke.besties_structured.business.persistence.entities.TypeEntity;
import es.tuke.besties_structured.business.persistence.repositories.TypeRepository;
import es.tuke.besties_structured.business.services.dtos.mapper;
import es.tuke.besties_structured.business.services.dtos.requestdtos.TypeRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.TypeResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements ITypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public TypeEntity getType(Long id) {
    
        return typeRepository.findById(id).orElseThrow(()->

            new IllegalArgumentException(
                "could not find type with id: " + id
                )

        );
    }

    @Override
    public TypeResponseDto save(TypeRequestDto dto) {
        TypeEntity typeEntity = new TypeEntity();

        typeEntity.setClase(dto.getClase());
        typeEntity.setDescripcion(dto.getDescripcion());
        
        typeRepository.save(typeEntity);

        return mapper.TypeToTypeResponseDto(typeEntity);
    }

    @Override
    public TypeResponseDto getTypeById(Long id) {
        TypeEntity typeEntity = getType(id);
        return mapper.TypeToTypeResponseDto(typeEntity);
    }

    @Override
    public List<TypeResponseDto> getAllTypes() {
        
        List<TypeEntity> types = StreamSupport
        .stream(typeRepository.findAll().spliterator(),false)
        .collect(Collectors.toList());

        return mapper.TypesToTypeResponseDtos(types);
    }

    @Override
    public TypeResponseDto delete(Long id) {
        TypeEntity typeEntity = getType(id);
        typeRepository.delete(typeEntity);

        return mapper.TypeToTypeResponseDto(typeEntity);
    }

    @Transactional
    @Override
    public TypeResponseDto edit(Long typeId, TypeRequestDto dto) {
        
        TypeEntity type = getType(typeId);

        type.setClase(dto.getClase());
        type.setDescripcion(dto.getDescripcion());
        
        TypeEntity entity = typeRepository.save(type);
        return mapper.TypeToTypeResponseDto(entity);
    }

   

}
