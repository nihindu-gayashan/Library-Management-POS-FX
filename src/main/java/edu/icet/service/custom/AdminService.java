package edu.icet.service.custom;

import edu.icet.dto.Admin;
import edu.icet.service.SuperService;

public interface AdminService extends SuperService {
    boolean save(Admin admin);

    boolean signIn(String username, String password);
}
