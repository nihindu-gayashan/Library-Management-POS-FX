package edu.icet.service.custom.impl;

import edu.icet.dto.Admin;
import edu.icet.entity.AdminEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.AdminDao;
import edu.icet.service.custom.AdminService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean save(Admin admin) {
        AdminEntity entity = new ModelMapper().map(admin, AdminEntity.class);
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        boolean save = adminDao.save(entity);

        return save;
    }
}
