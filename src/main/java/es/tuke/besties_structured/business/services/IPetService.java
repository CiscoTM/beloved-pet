package es.tuke.besties_structured.business.services;
import java.util.List;

import es.tuke.besties_structured.business.persistence.entities.PetEntity;
import es.tuke.besties_structured.business.services.dtos.requestdtos.PetRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.PetResponseDto;

public interface IPetService {

    public PetResponseDto savePet(PetRequestDto dto); 
    public List<PetResponseDto> getAllPetsActives();
    public List<PetResponseDto> getAllPetInactives();
    public PetEntity  getPet(Long id);
    public PetResponseDto getPetById(Long id);
    
    public PetResponseDto inactivePet(Long id);
    public PetResponseDto activePet(Long id);

    public PetResponseDto editPet(Long petId, PetRequestDto dto);
    
    public PetResponseDto addBreedToPet(Long petId, Long breedId); 
    public PetResponseDto deleteBreedFromPet(Long petId, Long breedId); 

    public PetResponseDto changeTypeToPet(Long typeId, Long petId); 
    // public PetResponseDto removeTypeToPet(Long typeId, Long petId); 
    
}
