package es.tuke.besties_structured.business.persistence.entities;


import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor  @Setter @Getter
@Table(name = "types")
public class TypeEntity{

    @Id
    @Column(name="type_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String clase;

    @Column(name = "description")
    private String descripcion;

    @JsonManagedReference
    @OneToMany(mappedBy = "tipo", fetch = FetchType.EAGER)
    private List<PetEntity> listaPetEntities = new ArrayList<>();

    public TypeEntity(String clase, String descripcion, List<PetEntity> listaPetEntities) {
        this.clase = clase;
        this.descripcion = descripcion;
        this.listaPetEntities = listaPetEntities;
    }
    
    public void addPet(PetEntity pet){
        listaPetEntities.add(pet);
    }

    public void removePet(PetEntity pet){
        listaPetEntities.remove(pet);
    }

    

    
}
