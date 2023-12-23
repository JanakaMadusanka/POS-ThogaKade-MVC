package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private String orderId;
    private String itemCode;
    private int qty;
    private Double unitPrice;

}
