package com.jd2.elibrary.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        emf = Persistence.createEntityManagerFactory("com.jd2.elibrary.model");
        return emf.createEntityManager();
    }

    public static void closeEMFactory(){
        emf.close();
    }
}
