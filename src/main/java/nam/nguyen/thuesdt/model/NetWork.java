package nam.nguyen.thuesdt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
public class NetWork {
    @Id
    private Integer id;
    private String name;
    private  String price;
}

