package nam.nguyen.thuesdt.controller;

import jakarta.servlet.http.HttpServletRequest;
import nam.nguyen.thuesdt.model.CodePhone;
import nam.nguyen.thuesdt.model.NetWork;
import nam.nguyen.thuesdt.model.Phone;
import nam.nguyen.thuesdt.model.User;
import nam.nguyen.thuesdt.repository.UserRepository;
import nam.nguyen.thuesdt.service.ApiService;
import nam.nguyen.thuesdt.service.CodePhoneService;
import nam.nguyen.thuesdt.service.NetWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class ServiceRestController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private CodePhoneService codePhoneService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private NetWorkService netWorkService;


    //thuê số
    @GetMapping("/useservice")
    public Phone sendServiceId(@RequestParam("id") String serviceId, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        Phone dataPhone = apiService.getPhone(serviceId);
        deductUserBalance(serviceId,username);
        return dataPhone;
    }
    @PostMapping("/hoantien")
    public void hoanUserBalance(@RequestParam("id") String id,HttpServletRequest request){
        Integer idlong = Integer.parseInt(id);
        String username = request.getUserPrincipal().getName();
        Optional<NetWork> netWorkOptional= netWorkService.getNetWorktById(idlong);
        Optional<User> userOptional = userRepository.findByUsername(username);
        Long price= Long.parseLong(netWorkOptional.get().getPrice());
            User user=userOptional.get();
            user.setUserBalance(userOptional.get().getUserBalance()+price);
            userRepository.save(user);
    }
    public boolean deductUserBalance(String id,String username){
        Integer idlong = Integer.parseInt(id);
        Optional<NetWork> netWorkOptional= netWorkService.getNetWorktById(idlong);
        Optional<User> userOptional = userRepository.findByUsername(username);
        Long price= Long.parseLong(netWorkOptional.get().getPrice());
        if(userOptional.get().getUserBalance()>=price){
            User user=userOptional.get();
            user.setUserBalance(userOptional.get().getUserBalance()-price);
            userRepository.save(user);
            return true;
        }else {
            return false;
        }

    }
        @GetMapping("/getcode/{requestid}")
        public ResponseEntity<CodePhone> getCodeByid(@PathVariable("requestid") String requestid,HttpServletRequest request) {
            String username = request.getUserPrincipal().getName();
            CodePhone codePhone = apiService.getCodePhone(requestid);
            //String username = request.getUserPrincipal().getName();
           // Optional<User> userOptional = userRepository.findByUsername(username);
           // User user = userOptional.get();
            codePhone.setUsername(username);
            codePhoneService.saveCodePhone(codePhone);
            //user.getCodePhones().add(codePhone);
            //userRepository.save(user);
            return ResponseEntity.ok().body(codePhone);
        }





}
