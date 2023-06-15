package nam.nguyen.thuesdt.service;

import nam.nguyen.thuesdt.model.CodePhone;
import nam.nguyen.thuesdt.model.NetWork;
import nam.nguyen.thuesdt.repository.CodePhoneRepository;
import nam.nguyen.thuesdt.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NetWorkService {
    @Autowired
    private NetworkRepository networkRepository;
    public List<NetWork> getAllNetWork(){
        return networkRepository.findAll();
    };
    public void saveNetWork(NetWork codePhone){
        networkRepository.save(codePhone);
    };
    public void saveAllNetWork(List<NetWork> codePhone){
        networkRepository.saveAll(codePhone);
    };
    public Optional<NetWork> getNetWorktById(Integer id){
        return networkRepository.findById(id);
    };

    public void deleteNetWorkById(Integer id){
        networkRepository.deleteById(id);
    };

}
