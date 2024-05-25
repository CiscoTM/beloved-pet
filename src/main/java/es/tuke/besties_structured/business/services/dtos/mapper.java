package es.tuke.besties_structured.business.services.dtos;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import es.tuke.besties_structured.business.persistence.entities.BreedEntity;
import es.tuke.besties_structured.business.persistence.entities.PetEntity;
import es.tuke.besties_structured.business.persistence.entities.TypeEntity;
import es.tuke.besties_structured.business.services.dtos.responsedtos.BreedResponseDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.PetResponseDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.TypeResponseDto;

public class mapper {
    
    public static PetResponseDto PetToPetResponseDto(PetEntity pet){

        PetResponseDto petResponseDto = new PetResponseDto();
        
        petResponseDto.setId(pet.getId());     
        petResponseDto.setActive(pet.isActive());
        petResponseDto.setFecha_nacimiento(pet.getFecha_nacimiento());
        petResponseDto.setNombre(pet.getNombre());
        petResponseDto.setSexo(pet.getSexo());
        petResponseDto.setTipo(pet.getTipo().getClase());

        Set<String> names = new HashSet<>();
        Set<BreedEntity>breeds = pet.getRazas();
        for (BreedEntity breed : breeds) {
            names.add(breed.getNombre());
        }
        petResponseDto.setRazas(names);
        return petResponseDto;

    }

    public static List<PetResponseDto> PetsToPetResponseDtos(List<PetEntity> pets){

        List<PetResponseDto> petResponseDtos = new ArrayList<>();

        for (PetEntity pet : pets) {
            petResponseDtos.add(PetToPetResponseDto(pet));
        }
        return petResponseDtos;

    }

    public static BreedResponseDto BreedToBreedResponseDto(BreedEntity breed){

        BreedResponseDto breedResponseDto = new BreedResponseDto();
        
        breedResponseDto.setId(breed.getId());     
        breedResponseDto.setNombre(breed.getNombre());
        breedResponseDto.setOrigen(breed.getOrigen());
        breedResponseDto.setLongevidad(breed.getLongevidad());
        breedResponseDto.setColor(breed.getColor());
        breedResponseDto.setPelaje(breed.getPelaje());
        breedResponseDto.setTamano(breed.getTamano());
        breedResponseDto.setAltura(breed.getAltura());
        breedResponseDto.setPeso(breed.getPeso());
        breedResponseDto.setTemperamento(breed.getTemperamento());
        breedResponseDto.setNecesidades(breed.getNecesidades());


        Set<String> names = new HashSet<>();
        Set<PetEntity>pets = breed.getSameBreed();
        for (PetEntity pet : pets) {
            names.add(pet.getNombre());
        }
        breedResponseDto.setMascotas(names);

        return breedResponseDto;

    }

    public static List<BreedResponseDto> BreedsToBreedResponseDtos(List<BreedEntity> breeds){

        List<BreedResponseDto> breedResponseDtos = new ArrayList<>();

        for (BreedEntity breed : breeds) {
            breedResponseDtos.add(BreedToBreedResponseDto(breed));
        }
        return breedResponseDtos;

    }

    public static TypeResponseDto TypeToTypeResponseDto(TypeEntity type){

        TypeResponseDto typeResponseDto = new TypeResponseDto();
        typeResponseDto.setId(type.getId());
        typeResponseDto.setClase(type.getClase());
        typeResponseDto.setDescripcion(type.getDescripcion());
        
        List<String> names = new ArrayList<>();
        List<PetEntity> pets = type.getListaPetEntities();
        for (PetEntity pet : pets) {
            names.add(pet.getNombre());            
        }
        
        typeResponseDto.setPets(names);

        return typeResponseDto;

    }

    public static List<TypeResponseDto> TypesToTypeResponseDtos(List<TypeEntity> types){

        
        List<TypeResponseDto> responseDtos = new ArrayList<>();
        for (TypeEntity type : types) {
            responseDtos.add(TypeToTypeResponseDto(type));
        }
        return responseDtos;

    }










}
