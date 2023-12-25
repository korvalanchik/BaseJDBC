package com.basejdbc.daoservice;

import com.basejdbc.model.*;
import com.basejdbc.prefs.Prefs;
import com.basejdbc.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {
    private final Storage storage;
    public static final String DB_FIND_LONGEST_PROJECT = "longestProject";
    public static final String DB_FIND_MAX_PROJECT_CLIENT = "maxProject";
    public static final String DB_FIND_MAX_SALARY_WORKER = "maxSalary";
    public static final String DB_FIND_YOUNGEST_ELDEST_WORKER = "youngestEldest";
    public static final String DB_PRINT_PROJECT_PRICE = "projectPrice";
    public ServiceDao(Storage storage) {
        this.storage = storage;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();
        try {
            String maxProject = new Prefs().getString(DB_FIND_MAX_PROJECT_CLIENT);
            String sql = String.join("\n", Files.readAllLines(Paths.get(maxProject)));
            try (Statement st = storage.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                    maxProjectCountClient.setName(rs.getString("NAME"));
                    maxProjectCountClient.setProjectCount(rs.getInt("PROJECT_COUNT"));
                    maxProjectCountClientList.add(maxProjectCountClient);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return maxProjectCountClientList;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();
        try {
            String maxSalary = new Prefs().getString(DB_FIND_MAX_SALARY_WORKER);
            String sql = String.join("\n", Files.readAllLines(Paths.get(maxSalary)));
            try (Statement st = storage.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                    maxSalaryWorker.setName(rs.getString("NAME"));
                    maxSalaryWorker.setSalary(rs.getInt("SALARY"));
                    maxSalaryWorkerList.add(maxSalaryWorker);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkerList;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        List<YoungestEldestWorkers> youngestEldestWorkersList = new ArrayList<>();
        try {
            String youngestEldest = new Prefs().getString(DB_FIND_YOUNGEST_ELDEST_WORKER);
            String sql = String.join("\n", Files.readAllLines(Paths.get(youngestEldest)));
            try (Statement st = storage.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    YoungestEldestWorkers youngestEldestWorkers = new YoungestEldestWorkers();
                    youngestEldestWorkers.setType(rs.getString("TYPE"));
                    youngestEldestWorkers.setName(rs.getString("NAME"));
                    youngestEldestWorkers.setBirthday(LocalDate.parse(rs.getString("BIRTHDAY")));
                    youngestEldestWorkersList.add(youngestEldestWorkers);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkersList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();
        try {
            String longestProj = new Prefs().getString(DB_FIND_LONGEST_PROJECT);
            String sql = String.join("\n", Files.readAllLines(Paths.get(longestProj)));
            try (Statement st = storage.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    LongestProject longestProject = new LongestProject();
                    longestProject.setName(rs.getString("NAME"));
                    longestProject.setMonthCount(rs.getInt("MONTH_COUNT"));
                    longestProjectList.add(longestProject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return longestProjectList;
    }

    public List<ProjectPrice> printProjectPrice() {
        List<ProjectPrice> projectPriceList = new ArrayList<>();
        try {
            String price = new Prefs().getString(DB_PRINT_PROJECT_PRICE);
            String sql = String.join("\n", Files.readAllLines(Paths.get(price)));
            try (Statement st = storage.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    ProjectPrice projectPrice = new ProjectPrice();
                    projectPrice.setName(rs.getString("NAME"));
                    projectPrice.setPrice(rs.getBigDecimal("PRICE"));
                    projectPriceList.add(projectPrice);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return projectPriceList;
    }
}
