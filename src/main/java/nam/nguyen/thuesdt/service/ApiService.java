package nam.nguyen.thuesdt.service;

import nam.nguyen.thuesdt.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    @Value("${api.token}")
    private  String token;
    public List<NetWork> getService (){
        String url = "https://api.viotp.com/service/getv2?token=" + token + "&country=vn";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ListNetWork> response = restTemplate.getForEntity(url , ListNetWork.class);
        if(response.getStatusCode().is2xxSuccessful()){
            ListNetWork listnetwork = response.getBody();
            return listnetwork.getData();
        }else {
            throw new RuntimeException("that bai");
        }
    }
    public Phone getPhone(String id){
        String url="https://api.viotp.com/request/getv2?token="+token +"&serviceId="+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DataPhone> response = restTemplate.getForEntity(url,DataPhone.class);
        if(response.getStatusCode().is2xxSuccessful()){
            DataPhone dataPhone = response.getBody();
            return dataPhone.getData();
        }else {
            throw new RuntimeException("loi");
        }
    }
    public CodePhone getCodePhone(String request_id){
        String url="https://api.viotp.com/session/getv2?requestId="+request_id+"&token="+token;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DataCodePhone> response = restTemplate.getForEntity(url,DataCodePhone.class);
        if(response.getStatusCode().is2xxSuccessful()){
            DataCodePhone dataCodePhone = response.getBody();
            return dataCodePhone.getData();
        }else{
            throw new RuntimeException("loi");
        }

    }

}
