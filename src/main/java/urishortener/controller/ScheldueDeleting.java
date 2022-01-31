package urishortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urishortener.entities.Reference1;
import urishortener.repositories.ReferenceRepository;

import java.util.List;

@Component
public class ScheldueDeleting {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Scheduled (fixedDelay = 7200000)
    private void deletingReference1() {
        List<Reference1> reference1List = referenceRepository.findAllByTimeBounded(true);
        for (Reference1 reference1:reference1List) {
            if (reference1.readyToDie()) {
                referenceRepository.delete(referenceRepository.findByReferenceShort(reference1.getReferenceShort()));
                System.out.println("чего-то стёрлось");
            }
        }
        System.out.println("ничего не стёрлось");
    }

}
