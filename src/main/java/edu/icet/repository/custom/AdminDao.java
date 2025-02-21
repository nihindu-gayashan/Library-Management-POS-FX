package edu.icet.repository.custom;

import edu.icet.entity.AdminEntity;
import edu.icet.repository.CrudDao;

import java.util.Optional;

public interface AdminDao extends CrudDao<AdminEntity> {
    Optional<AdminEntity> findByEmail(String email);

    String signIn(String username);
}
