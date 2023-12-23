package dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTm{
    private String id;
    private String name;
    private String address;
    private Double salary;
    private Button btn;
}
