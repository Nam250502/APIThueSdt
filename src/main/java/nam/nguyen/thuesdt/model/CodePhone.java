package nam.nguyen.thuesdt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodePhone {
    @Id
    @JsonProperty("ID")
    private int id;

    @JsonProperty("ServiceID")
    private int serviceId;

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("Price")
    private int price;

    @JsonProperty("SmsContent")
    private String smsContent;

    @JsonProperty("Status")
    private int status;

    @JsonProperty("CreatedTime")
    private String createdTime;

    @JsonProperty("IsSound")
    private boolean isSound;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("PhoneOriginal")
    private String phoneOriginal;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("CountryISO")
    private String countryISO;

    @JsonProperty("CountryCode")
    private String countryCode;

    private String username;

    // Constructors, getters, and setters

    // ...
}

