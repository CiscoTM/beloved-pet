package es.tuke.besties_structured.business.services.implementations;

import java.util.HashSet;
// import java.util.HashSet;
import java.util.List;
import java.util.Set;
// import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import es.tuke.besties_structured.business.persistence.entities.BreedEntity;
import es.tuke.besties_structured.business.persistence.entities.PetEntity;
import es.tuke.besties_structured.business.persistence.repositories.BreedRepository;
import es.tuke.besties_structured.business.services.IBreedService;
import es.tuke.besties_structured.business.services.IPetService;
import es.tuke.besties_structured.business.services.dtos.mapper;
import es.tuke.besties_structured.business.services.dtos.requestdtos.BreedRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.BreedResponseDto;
import jakarta.transaction.Transactional;

// COMO AuthorService 
@Service
public class BreedServiceImpl implements IBreedService{


    private final BreedRepository breedRepository;
    private final IPetService petService;
    
    public BreedServiceImpl(
        @Lazy BreedRepository breedRepository,
        @Lazy IPetService petService
        ) 
    {
        this.breedRepository = breedRepository;
        this.petService = petService;
    }

    @Override
    public BreedResponseDto getBreedById(Long id) {
        BreedEntity breed = getBreed(id);
        return mapper.BreedToBreedResponseDto(breed);
    }

    @Override
    public BreedEntity getBreed(Long id) {
       BreedEntity breed = breedRepository.findById(id).orElseThrow(() -> 
         new IllegalArgumentException(
            "cannot find breed with id: " + id
            )
       );
       return breed;
    }

    @Transactional
    @Override
    public BreedResponseDto addBreed(BreedRequestDto dto) {
        
        BreedEntity entity = new BreedEntity();
        entity.setNombre(dto.getNombre());
        entity.setAltura(dto.getAltura());
        entity.setColor(dto.getColor());
        entity.setLongevidad(dto.getLongevidad());
        entity.setNecesidades(dto.getNecesidades());
        entity.setOrigen(dto.getOrigen());
        entity.setPelaje(dto.getPelaje());
        entity.setPeso(dto.getPeso());
        entity.setTamano(dto.getTamano());
        entity.setTemperamento(dto.getTemperamento());

        // if(dto.getPetIds().isEmpty()){
        //     throw new IllegalArgumentException("you need atleast on pet");
        // }else {
        Set<PetEntity> pets = new HashSet<>();
        //     for (Long id : dto.getPetIds()) {
        //         PetEntity petEntity = petService.getPet(id);
        //         pets.add(petEntity);
        //     }
        entity.setSameBreed(pets);
        // }
        
        BreedEntity breed = breedRepository.save(entity);
        return mapper.BreedToBreedResponseDto(breed);
    }

    @Override
    public List<BreedResponseDto> getBreeds() {
        
        List<BreedEntity> breeds = StreamSupport
        .stream(breedRepository.findAll().spliterator(),false)
        .collect(Collectors.toList());
        
        return mapper.BreedsToBreedResponseDtos(breeds);

    }

    @Override
    public BreedResponseDto deleteBreed(Long id) {
        
        BreedEntity responseBreed = getBreed(id);
        BreedEntity deletedBreed = getBreed(id);
        breedRepository.delete(deletedBreed);
        
        return mapper.BreedToBreedResponseDto(responseBreed);
    }

    @Transactional
    @Override
    public BreedResponseDto editBreed(Long id, BreedRequestDto dto) {
        
        BreedEntity entity = getBreed(id);
        entity.setNombre(dto.getNombre());
        entity.setAltura(dto.getAltura());
        entity.setColor(dto.getColor());
        entity.setLongevidad(dto.getLongevidad());
        entity.setNecesidades(dto.getNecesidades());
        entity.setOrigen(dto.getOrigen());
        entity.setPelaje(dto.getPelaje());
        entity.setPeso(dto.getPeso());
        entity.setTamano(dto.getTamano());
        entity.setTemperamento(dto.getTemperamento());

        // if(!dto.getPetIds().isEmpty()){
        //     Set<PetEntity> pets = new HashSet<>();
        //     for(Long idPet: dto.getPetIds()){
        //         PetEntity pet = petService.getPet(idPet);
        //         pets.add(pet);
        //     }
        //     entity.setSameBreed(pets);
        // }

        BreedEntity breed = breedRepository.save(entity);
        return mapper.BreedToBreedResponseDto(breed);
    }

    @Override
    public BreedResponseDto addPetToBreed(Long breedId, Long petId) {
        
        BreedEntity breed = getBreed(breedId);
        PetEntity pet = petService.getPet(petId);

        if(pet.getRazas().contains(breed)){

            throw new IllegalArgumentException(
                "this breed is already assigned to this pet"
            );
        }
        breed.addPet(pet);       
        pet.addBreed(breed);

        return mapper.BreedToBreedResponseDto(breed);

    }

    @Override
    public BreedResponseDto deletePetToBreed(Long breedId, Long petId) {
        
        BreedEntity breed = getBreed(breedId);
        PetEntity pet = petService.getPet(petId);

        if(!(pet.getRazas().contains(breed))){

            throw new IllegalArgumentException(
                "breed does not have this pet"
            );
        }
        pet.removeBreed(breed);
        breed.removePet(pet);       

        return mapper.BreedToBreedResponseDto(breed);
    }

}
