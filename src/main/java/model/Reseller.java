package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "reseller")
    private Set<Plant> plants = new HashSet<>();

    public void addPlant(Plant p) {
        plants.add(p);
        p.setReseller(this);
    }
}
