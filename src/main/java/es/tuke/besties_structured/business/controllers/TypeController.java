package es.tuke.besties_structured.business.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.tuke.besties_structured.business.services.dtos.requestdtos.TypeRequestDto;
import es.tuke.besties_structured.business.services.dtos.responsedtos.TypeResponseDto;
import es.tuke.besties_structured.business.services.implementations.TypeServiceImpl;

@RestController
@RequestMapping("api/types")
public class TypeController {

    private final TypeServiceImpl service;

    public TypeController(TypeServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<TypeResponseDto> save(@RequestBody final TypeRequestDto type ){

        TypeResponseDto response = service.save(type);
        return new ResponseEntity<>(response,HttpStatus.CREATED);        

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TypeResponseDto> deleteType(
        @PathVariable final Long id
    )
    {
        TypeResponseDto response = service.delete(id);
        return new ResponseEntity<>(response,HttpStatus.OK);          
    }


    @GetMapping("{id}")
    public ResponseEntity<TypeResponseDto>getById(@PathVariable final Long id){

        TypeResponseDto response = service.getTypeById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);  

    }

    @GetMapping
    public ResponseEntity<List<TypeResponseDto>> getAll(){

        List<TypeResponseDto> response = service.getAllTypes();
        return new ResponseEntity<>(response,HttpStatus.OK);  

    }

    @PutMapping("{id}")
    public ResponseEntity<TypeResponseDto> update(
        @PathVariable final Long id,
        @RequestBody final TypeRequestDto dto){

            TypeResponseDto response = service.edit(id, dto);
            return new ResponseEntity<>(response,HttpStatus.OK); 

    }


}
