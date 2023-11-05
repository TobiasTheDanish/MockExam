package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResellerDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
}
