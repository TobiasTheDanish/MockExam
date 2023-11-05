package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlantDTO {
    private int id;
    private String plantType;
    private String plantName;
    private int maxHeight;
    private double price;
}
