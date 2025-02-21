package edu.icet.service.custom.impl;

import edu.icet.dto.Admin;
import edu.icet.entity.AdminEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.AdminDao;
import edu.icet.service.custom.AdminService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean save(Admin admin) {
        AdminEntity entity = new ModelMapper().map(admin, AdminEntity.class);
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        boolean save = adminDao.save(entity);

        return save;
    }

    @Override
    public boolean signIn(String username, String password) {
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        String encrypcode = adminDao.signIn(username);

        String encryptedpassword = null;
        try
        {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }


            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if (encrypcode.equals(encryptedpassword)) {
            return true;

        }else{
            return false;
        }
    }
}
