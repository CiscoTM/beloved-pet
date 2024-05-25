package es.tuke.besties_structured.business.services.implementations;

import java.util.HashSet;
import java.util.List;
// import java.util.Objects;
import java.util.Set;
import java.util.stream.*;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import es.tuke.besties_structured.business.persistence.entities.BreedEntity;
import es.tuke.besties_structured.business.persistence.entities.PetEntity;
import es.tuke.besties_structured.business.persistence.entities.TypeEntity;
import es.tuke.besties_structured.business.persistence.repositories.PetRepository;
import es.tuke.besties_structured.business.services.IBreedService;
import es.tuke.besties_structured.business.services.IPetService;
import es.tuke.besties_structured.business.services.ITypeService;
import es.tuke.besties_structured.business.services.dtos.mapper;
import es.tuke.besties_structured.business.services.dtos.requestdtos.PetRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.PetResponseDto;
import jakarta.transaction.Transactional;

@Service
public class PetServiceImpl implements IPetService{

    private final PetRepository petRepository;
    private final ITypeService typeService;
    private final IBreedService breedService;

    public PetServiceImpl(
              @Lazy PetRepository petRepository,
              @Lazy ITypeService typeService,
              @Lazy IBreedService breedService
    ) 
    {
        this.petRepository = petRepository;
        this.typeService = typeService;
        this.breedService = breedService;
    }

    @Transactional
    @Override
    public PetResponseDto savePet(PetRequestDto dto) {
        
        PetEntity entity = new PetEntity();
        entity.setNombre(dto.getNombre());
        entity.setActive(dto.isActive());
        entity.setFecha_nacimiento(dto.getFecha_nacimiento());
        entity.setSexo(dto.getSexo());

        if(dto.getRazasIds().isEmpty()){
            throw new IllegalArgumentException("you need atlest on breed");
        } else {
            Set<BreedEntity> breeds = new HashSet<>();
            Set<Long> ids = dto.getRazasIds();
            for (Long breedId : ids) {
                BreedEntity breed = breedService.getBreed(breedId);
                breeds.add(breed);
            }
            entity.setRazas(breeds);
        }
        
        if(dto.getTipoId() == null){
            throw new IllegalArgumentException("Pet need a breed");
        }

        TypeEntity type = typeService.getType(dto.getTipoId());
        entity.setTipo(type);

        PetEntity pet = petRepository.save(entity);
        return mapper.PetToPetResponseDto(pet);
    }



    @Override
    public PetResponseDto getPetById(Long id) {
        PetEntity pet = getPet(id);
        return mapper.PetToPetResponseDto(pet);
    }

    @Override
    public PetEntity getPet(Long id) {
        PetEntity petEntity = petRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException(
                "Pet with id: " + id + " could not be found")
        );

        return petEntity;
    }

    @Override
    public PetResponseDto inactivePet(Long id) {
        
        PetEntity pet = getPet(id);
        pet.setActive(false);

        petRepository.save(pet);
        
        return mapper.PetToPetResponseDto(pet);
    
    }

    @Override
    public PetResponseDto activePet(Long id) {
        
        PetEntity pet = getPet(id);
        pet.setActive(true);

        petRepository.save(pet);
        
        return mapper.PetToPetResponseDto(pet);
    
    }

    @Override
    public List<PetResponseDto> getAllPetsActives() {

        List<PetEntity> entities = StreamSupport
        .stream(petRepository.findAll().spliterator(),false)
        .filter(p -> filterActive(p))
        .collect(Collectors.toList());
        
        return mapper.PetsToPetResponseDtos(entities);

    }

    @Override
    public List<PetResponseDto> getAllPetInactives() {
        List<PetEntity> entities = StreamSupport
        .stream(petRepository.findAll().spliterator(),false)
        .filter(p -> filterNotActive(p))
        .collect(Collectors.toList());
        
        return mapper.PetsToPetResponseDtos(entities);
    }

    @Transactional
    @Override
    public PetResponseDto editPet(Long petId, PetRequestDto dto) {
        
        PetEntity petEntity = getPet(petId);
        petEntity.setNombre(dto.getNombre());
        petEntity.setFecha_nacimiento(dto.getFecha_nacimiento());
        petEntity.setSexo(dto.getSexo());
        
        if(!(dto.getRazasIds().isEmpty())){            
            Set<BreedEntity> breeds = new HashSet<>();
            for (Long breedId : dto.getRazasIds()) {
                BreedEntity breed = breedService.getBreed(breedId);
                breeds.add(breed);
            }
            petEntity.setRazas(breeds);
        }

        if(dto.getTipoId() != null){
            TypeEntity type = typeService.getType(dto.getTipoId());
            petEntity.setTipo(type);
        }
        
        PetEntity pet = petRepository.save(petEntity);
        return mapper.PetToPetResponseDto(pet);
    }

    @Override
    public PetResponseDto addBreedToPet(Long petId, Long breedId) {
        
        PetEntity pet = getPet(petId);
        BreedEntity breed = breedService.getBreed(breedId);

        if(pet.getRazas().contains(breed)){
            throw new IllegalArgumentException(
                "this breed is already assigned to this pet"
                );
        }
        breed.addPet(pet);
        pet.addBreed(breed);

        PetEntity entity = petRepository.save(pet);

        return mapper.PetToPetResponseDto(entity);
    }

    @Override
    public PetResponseDto deleteBreedFromPet(Long petId, Long breedId) {
        
        PetEntity pet = getPet(petId);
        BreedEntity breed = breedService.getBreed(breedId);
        
        if(!(pet.getRazas().contains(breed))){
            throw new IllegalArgumentException("pet does not have this breed");
        }
        pet.removeBreed(breed);
        breed.removePet(pet);

        PetEntity entity = petRepository.save(pet);
        return mapper.PetToPetResponseDto(entity);
    }

    @Override
    public PetResponseDto changeTypeToPet(Long typeId, Long petId) {
        
        PetEntity pet = getPet(petId);
        TypeEntity type = typeService.getType(typeId);

        
        
        if(pet.getTipo().equals(type)){
            throw new IllegalArgumentException("pet already has this type");
        }

        type.addPet(pet);
        pet.setTipo(type);

        petRepository.save(pet);

        return mapper.PetToPetResponseDto(pet);
    }

    // @Override
    // public PetResponseDto removeTypeToPet(Long typeId, Long petId) {
        
    //     PetEntity pet = getPet(petId);
    //     TypeEntity type = typeService.getType(typeId);
        
    //     if(Objects.isNull(pet.getTipo())){
    //         throw new IllegalArgumentException("pet does not have a type");
    //     }
        
    //     pet.setTipo(null);
    //     type.removePet(pet);
    //     return mapper.PetToPetResponseDto(pet);

    // }

    private static boolean filterActive(PetEntity p){

        return p.isActive();

    }
    
    private static boolean filterNotActive(PetEntity p){

        return !p.isActive();

    }


    

}
