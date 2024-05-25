package es.tuke.besties_structured.business.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class MemberEntity {
    
    private Long id;
    private String name;
    private String email;

    
    private Set<PetEntity> mascotas = new HashSet<>();
    private UserEntity user;

    public MemberEntity(Long id, String name, String email, Set<PetEntity> mascotas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mascotas = mascotas;
    }

    public void addPet(PetEntity petEntity){
        mascotas.add(petEntity);
    }

    public void removePet(PetEntity petEntity){
        mascotas.remove(petEntity);
    }

    
}
