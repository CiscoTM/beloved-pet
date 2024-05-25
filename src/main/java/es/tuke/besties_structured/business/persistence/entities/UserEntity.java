package es.tuke.besties_structured.business.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class UserEntity {

    private Long id;
    private String email;
    private String password;

    
    private MemberEntity member;

    public UserEntity(Long id, String email, String password, MemberEntity member) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.member = member;
    }


    

}
