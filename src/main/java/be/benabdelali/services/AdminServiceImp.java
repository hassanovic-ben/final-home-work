package be.benabdelali.services;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;
import be.benabdelali.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 3/06/2017.
 */

@Service
@Transactional
public class AdminServiceImp implements AdminService {


    @Autowired
    AdminRepository adminRepository;


    @Override
    public List<Admin> findAll() {

        return adminRepository.findAll();
    }

    @Override
    public Admin findByUserNameAndPassword(String username, String password) {

        List<Admin> usersFound = adminRepository.findByUserNameAndPassword(username, password);
        if (usersFound.isEmpty()) {
            return null;
        }
        return usersFound.get(0);
    }

    @Override
    public Admin createAdmin(Admin admin) {

        if (adminRepository.findAll().contains(admin)) {
            return null;
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin findByUserName(String username) {

        List<Admin> adminFound = adminRepository.findByUserName(username);
        if (adminFound.isEmpty()) {
            return null;
        }
        return adminFound.get(0);
    }

    @Override
    public Admin getById(Long idUser) {

        Admin admin = adminRepository.findOne(idUser);
        return admin;
    }

    @Override
    public List<Admin> getByType(String type) {

        return adminRepository.findByType(type);
    }

    @Override

    public Long deleteAdmin(Long id) {
        Admin foundAdmin = adminRepository.findOne(id);
        if (foundAdmin != null) {
            adminRepository.delete(id);
            return id;
        }
        return null;
    }

    @Override
    public int totalBookSold() {
        //return clientRepository.bestSeller();
        return adminRepository.totalBookSeller();
    }

    @Override
    public List<Client> bestSellerForTheBook() {
        return adminRepository.bestSellerForTheBook();
    }

    @Override
    public List getAdminByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }


}
