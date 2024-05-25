package es.tuke.besties_structured.business.persistence.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import es.tuke.besties_structured.business.persistence.SexoEnum;

import java.util.HashSet;


@Entity
@NoArgsConstructor @Setter @Getter
@Table(name = "pets")
public class PetEntity implements Serializable{ //extends RepresentationModel<PetEntity> implements Serializable{

    @Id
    @Column(name="pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 28)
    private String nombre;

    @Column(name = "birth_date")
    private Date fecha_nacimiento;

    @Column(name = "active")
    private boolean active=true;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @ManyToOne(targetEntity = TypeEntity.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeEntity tipo = new TypeEntity();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "breed_pet",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "breed_id")
    )
    private Set<BreedEntity> razas = new HashSet<>();


    public PetEntity(Long id, String nombre, Date fecha_nacimiento, boolean active, SexoEnum sexo, TypeEntity tipo,
            Set<BreedEntity> razas) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.active = active;
        this.sexo = sexo;
        this.tipo = tipo;
        this.razas = razas;
    }

    public void addBreed(BreedEntity breedEntity){
        razas.add(breedEntity);
    }

    public void removeBreed(BreedEntity breedEntity){
        razas.remove(breedEntity);
    }


    

 

}
