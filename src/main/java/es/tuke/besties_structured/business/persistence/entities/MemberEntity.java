package es.tuke.besties_structured.business.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Setter @Getter
@Table(name = "members")
public class MemberEntity {
    
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private Set<PetEntity> mascotas = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private UserEntity user;
    
    public MemberEntity(Long id, String name, String email, Set<PetEntity> mascotas, UserEntity user) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mascotas = mascotas;
        this.user = user;
    }

    public void addPet(PetEntity petEntity){
        mascotas.add(petEntity);
    }

    public void removePet(PetEntity petEntity){
        mascotas.remove(petEntity);
    }

    
}
