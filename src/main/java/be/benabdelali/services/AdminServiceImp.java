package be.benabdelali.services;

import be.benabdelali.model.Admin;
import be.benabdelali.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        List<Admin> users = adminRepository.findAll();
        return users;
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

        Admin adminFound = adminRepository.findOne(idUser);
        return adminFound;
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
    public List getAdminByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }
}
