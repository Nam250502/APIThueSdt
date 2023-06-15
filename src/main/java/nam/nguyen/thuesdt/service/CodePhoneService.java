package nam.nguyen.thuesdt.service;

import nam.nguyen.thuesdt.model.CodePhone;
import nam.nguyen.thuesdt.repository.CodePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CodePhoneService {
    @Autowired
    private CodePhoneRepository codePhoneRepository;
    public List<CodePhone> getAllCodePhone(){
        return codePhoneRepository.findAll();
    };
    public void saveCodePhone(CodePhone codePhone){
        codePhoneRepository.save(codePhone);
    };
    public Optional<CodePhone> getCodePhonetById(Long id){
        return codePhoneRepository.findById(id);
    };

    public void deleteCodePhoneById(Long phoneid){
        codePhoneRepository.deleteById(phoneid);
    };
}
