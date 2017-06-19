package be.benabdelali.services;

import be.benabdelali.model.Admin;

import java.util.List;



public interface AdminService {

    List<Admin> findAll();

    Admin findByUserNameAndPassword(String userName, String password);

    Admin createAdmin(Admin admin);

    Admin findByUserName(String username);

    Admin getById(Long idUser);

    List<Admin> getByType(String type);

    Long deleteAdmin(Long id);

    List<Admin> getAdminByUserName(String userName);
}
