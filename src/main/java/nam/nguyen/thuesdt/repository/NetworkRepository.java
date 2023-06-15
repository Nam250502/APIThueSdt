package nam.nguyen.thuesdt.repository;

import nam.nguyen.thuesdt.model.NetWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<NetWork,Integer> {
}
