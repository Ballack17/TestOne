package urishortener.repositories;

import org.springframework.data.repository.CrudRepository;
import urishortener.entities.ReferenceNew;
import urishortener.entities.Visit;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    Visit findOneByVisitIp(String visit_ip);
    List<Visit> findAllByReferenceNew (ReferenceNew referenceNew);
}
