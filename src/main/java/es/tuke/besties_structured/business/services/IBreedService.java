package es.tuke.besties_structured.business.services;

import java.util.List;

import es.tuke.besties_structured.business.persistence.entities.BreedEntity;
import es.tuke.besties_structured.business.services.dtos.requestdtos.BreedRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.BreedResponseDto;

public interface IBreedService {
    
    public BreedResponseDto getBreedById(Long id);
    public BreedEntity getBreed(Long id);
    public BreedResponseDto addBreed(BreedRequestDto dto);
    public List<BreedResponseDto> getBreeds();
    public BreedResponseDto deleteBreed(Long id);
    public BreedResponseDto editBreed(Long id, BreedRequestDto dto); 

    public BreedResponseDto addPetToBreed(Long breedId, Long petId); 
    public BreedResponseDto deletePetToBreed(Long breedId, Long petId); 
     
}
