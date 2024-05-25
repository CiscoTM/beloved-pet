package es.tuke.besties_structured.business.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.tuke.besties_structured.business.services.IPetService;
import es.tuke.besties_structured.business.services.dtos.requestdtos.PetRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.PetResponseDto;

@RestController
@RequestMapping("api/pets")
public class PetController {

    private final IPetService service;

    public PetController(IPetService service) {
        this.service = service;
    }

    @PostMapping("/addpet")
    public ResponseEntity<PetResponseDto> save(
        @RequestBody final PetRequestDto request
        ){
        PetResponseDto response = service.savePet(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> getById(
        @PathVariable final Long id
        ){
        PetResponseDto response = service.getPetById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/actives")
    public ResponseEntity<List<PetResponseDto>> getAll(){
        List<PetResponseDto> response = service.getAllPetsActives();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/inactives")
    public ResponseEntity<List<PetResponseDto>> getAllInactive(){
        
        List<PetResponseDto> response = service.getAllPetInactives();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping("active/{id}")
    public ResponseEntity<PetResponseDto> activate(@PathVariable final Long id) {
        
        PetResponseDto response = service.activePet(id);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping("inactive/{id}")
    public ResponseEntity<PetResponseDto> inActivate(@PathVariable final Long id) {
        
        PetResponseDto response = service.inactivePet(id);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<PetResponseDto> update(
        @PathVariable final Long id,
        @RequestBody  final PetRequestDto pet
    )
    {

        PetResponseDto response = service.editPet(id, pet);

        return new ResponseEntity<>(response, HttpStatus.OK);      
    }

    @PutMapping("/changetype/{idType}/{idPet}")
    private ResponseEntity<PetResponseDto> changeTypeToPet(
        @PathVariable final Long idType,
        @PathVariable final Long idPet
    )
    {
        PetResponseDto response = service.changeTypeToPet(idType, idPet);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // @PutMapping("/removetype/{idType}/{idPet}")
    // private ResponseEntity<PetResponseDto> removeTypeToPet(
    //     @PathVariable Long idType,
    //     @PathVariable Long idPet
    // )
    // {
    //     PetResponseDto response = service.removeTypeToPet(idType, idPet);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    @PutMapping("/addbreed/{idBreed}/{idPet}")
    private ResponseEntity<PetResponseDto> addBreedToPet(
        @PathVariable final Long idBreed,
        @PathVariable final Long idPet
    )
    {
        PetResponseDto response = service.addBreedToPet(idPet, idBreed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/removebreed/{idBreed}/{idPet}")
    private ResponseEntity<PetResponseDto> removeBreedToPet(
        @PathVariable final Long idBreed,
        @PathVariable final Long idPet
    )
    {
        PetResponseDto response = service.deleteBreedFromPet(idPet, idBreed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    
}
