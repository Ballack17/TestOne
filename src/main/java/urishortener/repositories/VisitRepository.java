package urishortener.repositories;

import org.springframework.data.repository.CrudRepository;
import urishortener.entities.Reference1;
import urishortener.entities.Visit;

import java.util.HashSet;
import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    Visit findOneByVisitIp(String visit_ip);
    List<Visit> findAllByReference1 (Reference1 reference1);

}
