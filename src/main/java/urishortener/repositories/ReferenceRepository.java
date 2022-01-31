package urishortener.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import urishortener.entities.Reference1;
import urishortener.entities.User;

import java.util.List;

public interface ReferenceRepository extends CrudRepository<Reference1, Long> {
//    Page<Reference> findAllByIdUserBetween(Pageable pageable, double min, double max);
    List<Reference1> findAllByIdUser (User idUser);
    Reference1 findByReferenceShort (String referenceShort);
    List<Reference1> findAll();
    List<Reference1> findAllByTimeBounded (Boolean thing);
}
