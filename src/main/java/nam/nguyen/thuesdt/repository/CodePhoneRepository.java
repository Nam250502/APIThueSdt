package nam.nguyen.thuesdt.repository;

import nam.nguyen.thuesdt.model.CodePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodePhoneRepository extends JpaRepository<CodePhone,Long> {
    List<CodePhone> findByUsername(String username);
}
