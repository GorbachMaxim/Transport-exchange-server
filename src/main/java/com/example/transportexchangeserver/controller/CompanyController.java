package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.MessageResponse;
import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping(value = "/my_company")
    public @ResponseBody CompanyDTO myCompany(HttpServletRequest request){
        CompanyDTO company = companyService.getCompanyByUser(request);
        return company;
    }

    @PostMapping (value = "/my_company")
    public @ResponseBody ResponseEntity<?>  AddMyCompany(@RequestBody CompanyDTO companyDTO, HttpServletRequest request){
        if(companyService.addCompany(request, companyDTO))
            return ResponseEntity.ok(new MessageResponse("Company Created"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Can't create a company"));
    }

    @PutMapping (value = "/my_company")
    public @ResponseBody ResponseEntity<?>  UpdateMyCompany(@RequestBody CompanyDTO companyDTO, HttpServletRequest request){
        if(companyService.updateCompany(request, companyDTO))
            return ResponseEntity.ok(new MessageResponse("Company Edited"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Can't edite a company"));
    }

    @DeleteMapping("/my_company")
    public ResponseEntity<?> deleteMyCompany(HttpServletRequest request){
        if(companyService.deleteCompanyByUser(request))
            return ResponseEntity.ok(new MessageResponse("Company Deleted"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Can't delete a company"));
    }

    @GetMapping(value = "/companies")
    public @ResponseBody List<CompanyDTO> getAllCompanies(){
        List<CompanyDTO> companies = companyService.getAllCompanies();
        companies.sort((o1, o2) -> {
            double i = o2.getAvgScore()-o1.getAvgScore();
            if(i>0)
                i++;
            else if (i<0) {
                i--;
            }
            return (int) i;
        });
        return companies;
    }

    @GetMapping(value = "/companies/{id}")
    public @ResponseBody CompanyDTO getCompany(@PathVariable long id){
        CompanyDTO company = companyService.getCompanyById(id);
        return company;
    }

    @DeleteMapping("/companies/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCompany (@PathVariable long id){
        if(companyService.deleteCompanyById(id))
            return ResponseEntity.ok(new MessageResponse("CarOffer Deleted"));
        else
            return ResponseEntity.status(403).body(new MessageResponse("Not your company"));
    }
}
