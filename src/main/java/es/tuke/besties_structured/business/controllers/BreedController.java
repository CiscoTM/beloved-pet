package es.tuke.besties_structured.business.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.tuke.besties_structured.business.services.IBreedService;
import es.tuke.besties_structured.business.services.dtos.requestdtos.BreedRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.BreedResponseDto;

@RestController
@RequestMapping(value = "api/breeds")
public class BreedController {

    private final IBreedService service;

    public BreedController(IBreedService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<BreedResponseDto> create(@RequestBody final BreedRequestDto breed){
        
        BreedResponseDto response = service.addBreed(breed);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
            
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedResponseDto> getBreedById(@PathVariable final Long id){

        BreedResponseDto response = service.getBreedById(id);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }
    

    @GetMapping
    public ResponseEntity<List<BreedResponseDto>> getAllBreed(){
        List<BreedResponseDto> response = service.getBreeds();

        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BreedResponseDto> deleteBreed(@PathVariable final Long id ){
       
        BreedResponseDto response = service.deleteBreed(id);

        return new ResponseEntity<>(response,HttpStatus.OK);
        
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<BreedResponseDto> editBreed(
        @RequestBody final BreedRequestDto breed,
        @PathVariable final Long id
    )
    {
        BreedResponseDto response = service.editBreed(id, breed);

        return new ResponseEntity<>(response,HttpStatus.CREATED);        
    }

    @PostMapping("/add/{idPet}/to/{idBreed}")
    public ResponseEntity<BreedResponseDto> addPetToBreed(
        @PathVariable final Long idPet,
        @PathVariable final Long idBreed
    )
    {
        BreedResponseDto response = service.addPetToBreed(idBreed, idPet);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/remove/{idPet}/to/{idBreed}")
    public ResponseEntity<BreedResponseDto> removePetToBreed(
        @PathVariable final Long idPet,
        @PathVariable final Long idBreed
    )
    {
        BreedResponseDto response = service.deletePetToBreed(idBreed, idPet);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
           
}
