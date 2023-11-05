package model;

import dto.PlantDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plant_type")
    private String plantType;

    @Column(name = "plant_name")
    private String plantName;

    @Column(name = "max_height")
    private int maxHeight;

    @Column(name = "price")
    private double price;

    @ToString.Exclude
    @ManyToOne
    private Reseller reseller;

    public Plant(PlantDTO plant) {
        this.plantType = plant.getPlantType();
        this.plantName = plant.getPlantName();
        this.maxHeight = plant.getMaxHeight();
        this.price = plant.getPrice();
    }
}
