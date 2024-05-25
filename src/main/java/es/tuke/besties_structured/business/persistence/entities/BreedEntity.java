package es.tuke.besties_structured.business.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "breeds")
@Setter @Getter @NoArgsConstructor
@Table(name = "breeds")
public class BreedEntity implements Serializable {
    
    @Id
    @Column(name = "breed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombre;
    @Column(name = "origin")
    private String origen;
    @Column(name = "longevity")
    private String longevidad;
    private String color;
    @Column(name = "fur")
    private String pelaje;
    @Column(name = "size")
    private String tamano;
    @Column(name = "height")
    private String altura;
    @Column(name = "weight")
    private String peso;
    @Column(name = "temperament")
    private String temperamento;
    @Column(name = "needs")
    private String necesidades;


    @ManyToMany(mappedBy = "razas", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY )
    private Set<PetEntity> sameBreed = new HashSet<>();

    public BreedEntity(String nombre, String origen, String longevidad, String color, String pelaje, String tamano,
            String altura, String peso, String temperamento, String necesidades, Set<PetEntity> sameBreed) {
        this.nombre = nombre;
        this.origen = origen;
        this.longevidad = longevidad;
        this.color = color;
        this.pelaje = pelaje;
        this.tamano = tamano;
        this.altura = altura;
        this.peso = peso;
        this.temperamento = temperamento;
        this.necesidades = necesidades;
        this.sameBreed = sameBreed;
    };


    public void addPet (PetEntity petEntity){
        sameBreed.add(petEntity);
    }

    public void removePet (PetEntity petEntity){
        sameBreed.remove(petEntity);
    }
    


}
