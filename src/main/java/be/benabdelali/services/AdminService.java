package be.benabdelali.services;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;

import java.util.List;

/**
 * Created by hassan on 3/06/2017.
 */

public interface AdminService {

    List<Admin> findAll();

    Admin findByUserNameAndPassword(String userName, String password);

    Admin createAdmin(Admin admin);

    Admin findByUserName(String username);

    Admin getById(Long idUser);


    List<Admin> getByType(String type);

    Long deleteAdmin(Long id);

    int totalBookSold();

    List<Client> bestSellerForTheBook();

    List<Admin> getAdminByUserName(String userName);
}
