package urishortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import urishortener.entities.Visit;
import urishortener.repositories.ReferenceRepository;
import urishortener.repositories.VisitRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@RestController ("api/v1/redirect")
@WebServlet
//@RequestMapping ("/1")
public class RedirectController extends HttpServlet {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/{id}")
    protected void doGet(@PathVariable(name = "id") String referenceShort, HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {

        referenceRepository.findByReferenceShort(referenceShort).wasUsed();
        referenceRepository.save(referenceRepository.findByReferenceShort(referenceShort));
        System.out.println(referenceRepository.findByReferenceShort(referenceShort).getWasUsed());


        List<Visit> visitsList = visitRepository.findAllByReference1(referenceRepository.findByReferenceShort(referenceShort));
        if (!visitsList.contains(visitRepository.findOneByVisitIp(request.getRemoteAddr()))) {
            referenceRepository.findByReferenceShort(referenceShort).wasUniqueUsed();
            referenceRepository.save(referenceRepository.findByReferenceShort(referenceShort));
        }
        Visit visitNew = new Visit(request.getRemoteAddr(),referenceRepository.findByReferenceShort(referenceShort));
        visitRepository.save(visitNew);


        response.sendRedirect(referenceRepository.findByReferenceShort(referenceShort).getReferenceUser());
    }
}
