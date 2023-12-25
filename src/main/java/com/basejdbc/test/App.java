package com.basejdbc.test;

import com.basejdbc.daoservice.ServiceDao;
import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.DatabasePopulateService;
import com.basejdbc.storage.Storage;

@SuppressWarnings("java:S106")
public class App {
    public static void main(String[] args) {
        Storage storage = Storage.getInstance();
        new DatabaseInitService().initDb(storage);
        new DatabasePopulateService().setDbPopulate(storage);

        ServiceDao serviceDao = new ServiceDao(storage);
        System.out.println(serviceDao.findMaxProjectsClient());
        System.out.println(serviceDao.findMaxSalaryWorker());
        System.out.println(serviceDao.findYoungestEldestWorkers());
        System.out.println(serviceDao.findLongestProject());
        System.out.println(serviceDao.printProjectPrice());

        storage.close();
    }
}
