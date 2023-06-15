package nam.nguyen.thuesdt.controller;





import jakarta.servlet.http.HttpServletRequest;
import nam.nguyen.thuesdt.model.NetWork;
import nam.nguyen.thuesdt.model.User;
import nam.nguyen.thuesdt.repository.UserRepository;
import nam.nguyen.thuesdt.service.ApiService;
import nam.nguyen.thuesdt.service.CodePhoneService;
import nam.nguyen.thuesdt.service.NetWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private CodePhoneService codePhoneService;
    @Autowired
    private NetWorkService netWorkService;
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/getservice")
    public void listnetwork(){
        List<NetWork> listnetwork = apiService.getService();
        netWorkService.saveAllNetWork(listnetwork);
    }
    @RequestMapping("/getallservice")
    public String listallnetwork(Model model,HttpServletRequest request){

        List<NetWork> listnetwork = netWorkService.getAllNetWork();
        model.addAttribute("lisnetwork",listnetwork);
        return "service";
    }

    @RequestMapping("/getserviceuser")
    public String listanetworkuser(Model model){
        List<NetWork> listnetwork = netWorkService.getAllNetWork();
        model.addAttribute("lisnetwork",listnetwork);
        return "getserviceuser";
    }
    @RequestMapping("/pay")
    public String Pay(Model model,HttpServletRequest request){
        model.addAttribute("username",request.getUserPrincipal().getName());
        return "pay";
    }


}
