package urishortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urishortener.entities.ReferenceNew;
import urishortener.repositories.ReferenceRepository;

import java.util.List;

@Component
public class ScheduleDeleting {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Scheduled(fixedDelay = 7200000)
    private void deletingReference1() {
        List<ReferenceNew> referenceNewList = referenceRepository.findAllByTimeBounded(true);
        for (ReferenceNew referenceNew : referenceNewList) {
            if (referenceNew.readyToDie()) {
                referenceRepository.delete(referenceRepository.findByReferenceShort(referenceNew.getReferenceShort()));
                System.out.println("чего-то стёрлось");
            }
        }
        System.out.println("ничего не стёрлось");
    }

}
