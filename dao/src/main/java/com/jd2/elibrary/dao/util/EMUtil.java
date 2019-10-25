package com.jd2.elibrary.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("com.jd2.elibrary");
        }
        return emf.createEntityManager();
    }

    public static void closeEMFactory() {
        emf.close();
    }
}
