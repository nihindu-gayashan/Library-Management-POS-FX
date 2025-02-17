package edu.icet.service;

import edu.icet.service.custom.impl.AdminServiceImpl;
import edu.icet.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case ADMIN: return (T) new AdminServiceImpl();
        }
        return null;
    }
}
