package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Person> personList;
}
