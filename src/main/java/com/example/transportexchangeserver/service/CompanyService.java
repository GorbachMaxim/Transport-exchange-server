package com.example.transportexchangeserver.service;

import com.example.transportexchangeserver.dto.mapper.CompanyMapper;
import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.model.Company;
import com.example.transportexchangeserver.model.User;
import com.example.transportexchangeserver.repository.CarOfferRepository;
import com.example.transportexchangeserver.repository.CargoOfferRepository;
import com.example.transportexchangeserver.repository.CompanyRepository;
import com.example.transportexchangeserver.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final UserDetailsServiceImpl userService;

    private final CompanyRepository companyRepository;

    private final CarOfferRepository carOfferRepository;

    private final CargoOfferRepository cargoOfferRepository;

    private final CompanyMapper companyMapper;

    public CompanyDTO getCompanyByUser(HttpServletRequest request) {

        User user = userService.getUserFromJWT(request);
        if(user == null) {
           return null;
        }

        Optional<Company> company = companyRepository.getCompanyByUser_Id(user.getId());
        if(company.isPresent()){
            CompanyDTO companyDTO = companyMapper.toDTO(company.get());
            return companyDTO;
        }
        else {
            return null;
        }
    };


    public Boolean addCompany(HttpServletRequest request, CompanyDTO companyDTO) {

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }
        if(companyRepository.getCompanyByUser_Id(user.getId()).isPresent()){
            return false;
        }
        Company company = companyMapper.toModel(companyDTO);
        company.setUser(user);
        companyRepository.save(company);
        return true;
    };

    public Boolean updateCompany(HttpServletRequest request, CompanyDTO companyDTO) {

        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }
        Optional<Company> firstCompany = companyRepository.getCompanyByUser_Id(user.getId());

        if(!firstCompany.isPresent()){
            return false;
        }
        Company company = companyMapper.toModel(companyDTO);

        company.setId(firstCompany.get().getId());
        company.setUser(user);
        companyRepository.save(company);
        return true;
    };

    public  List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findByOrderByNameAsc();
        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for(Company company : companies){
            companiesDTO.add(companyMapper.toDTO(company));
        }
        return companiesDTO;
    };

    @Transactional
    public boolean deleteCompanyByUser(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        if(user == null) {
            return false;
        }
        Optional<Company> company = companyRepository.getCompanyByUser_Id(user.getId());
        if(!company.isPresent()){
            return false;
        }
        carOfferRepository.deleteByCompany_Id(company.get().getId());
        cargoOfferRepository.deleteByCompany_Id(company.get().getId());
        companyRepository.delete(company.get());
        return true;
    }

    @Transactional
    public boolean deleteCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            return false;
        }
        carOfferRepository.deleteByCompany_Id(company.get().getId());
        cargoOfferRepository.deleteByCompany_Id(company.get().getId());
        companyRepository.deleteById(id);
        return true;
    }

    public CompanyDTO getCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            CompanyDTO companyDTO = companyMapper.toDTO(company.get());
            return companyDTO;
        }
        else {
            return null;
        }
    }
}
