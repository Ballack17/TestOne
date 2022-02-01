package urishortener.repositories;

import org.springframework.data.repository.CrudRepository;
import urishortener.entities.ReferenceNew;
import urishortener.entities.User;

import java.util.List;

public interface ReferenceRepository extends CrudRepository<ReferenceNew, Long> {
    List<ReferenceNew> findAllByIdUser (User idUser);
    ReferenceNew findByReferenceShort (String referenceShort);
    List<ReferenceNew> findAll();
    List<ReferenceNew> findAllByTimeBounded (Boolean thing);
}
