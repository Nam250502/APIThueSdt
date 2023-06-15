package nam.nguyen.thuesdt.controller;


import jakarta.servlet.http.HttpServletRequest;
import nam.nguyen.thuesdt.model.CodePhone;
import nam.nguyen.thuesdt.model.User;
import nam.nguyen.thuesdt.repository.CodePhoneRepository;
import nam.nguyen.thuesdt.repository.UserRepository;
import nam.nguyen.thuesdt.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CodePhoneRepository codePhoneRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailService emailService;


    @GetMapping("/view")
    public String viewprofile(HttpServletRequest request, Model model) {
        String username = request.getUserPrincipal().getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        List<CodePhone>  listhistoryservice = codePhoneRepository.findByUsername(username);
        model.addAttribute("coutphone",listhistoryservice.stream().count());
        User user=userOptional.get();

        model.addAttribute("user",user);
        return "viewprofile";
    }
    @RequestMapping("/historyservice")
    public  String viewHistoryService(Model model,HttpServletRequest request){
        String username= request.getUserPrincipal().getName();
        List<CodePhone>  listhistoryservice = codePhoneRepository.findByUsername(username);
        model.addAttribute("listhistoryservice",listhistoryservice);
        return "listhistoryservice";

    }
    @PostMapping("/update")
    private String updateuser(@RequestParam("name") String name,@RequestParam("username") String username,@RequestParam("email") String email){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.get();
            String emailsend=user.getEmail();
            String noidung="bạn đã thay đổi thông tin tài khoản thành email: "+email +" tên: "+name;
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
            senMail(emailsend,noidung);
            return "redirect:/user/view";
    }

    @PostMapping("/naptien")
    @ResponseBody
    private String Naptien(@RequestParam("username") String username, @RequestParam("sotien") Long sotien) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserBalance(user.getUserBalance() + sotien);
            userRepository.save(user);
            return "success"; // Phản hồi thành công
        }
        return "error"; // Phản hồi lỗi (nếu người dùng không tồn tại)
    }

    @RequestMapping("/viewnaptien")
    private String Viewnaptien(){
        return "naptien";
    }

    public void senMail(String to, String text){
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setSubject("Thông Báo");
            mailMessage.setText(text);
            javaMailSender.send(mailMessage);
    }
}
