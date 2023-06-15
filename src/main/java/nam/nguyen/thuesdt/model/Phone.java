package nam.nguyen.thuesdt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Phone {
    private String phone_number ;
    private String re_phone_number;
    private String countryISO ;
    private String countryCode;
    private Long balance;
    private String request_id ;
}
