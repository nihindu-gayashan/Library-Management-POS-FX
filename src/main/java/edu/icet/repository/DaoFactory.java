package edu.icet.repository;

import edu.icet.repository.custom.impl.AdminDaoImpl;
import edu.icet.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case ADMIN:return (T) new AdminDaoImpl();
        }
        return null;

    }
}