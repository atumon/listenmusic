package com.sky.simple.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Configuration
public class MahoutConfig {
    private MysqlDataSource getDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDatabaseName("listenmusic");

        return dataSource;
    }

    @Bean(autowire = Autowire.BY_NAME,value = "MySQLJDBCTagDataModel")
    public DataModel getMySQLJDBCTagDataModel(){
        DataModel dataModel = new MySQLJDBCDataModel(getDataSource(), "rescore", "user_id", "tag", "score", "time");
        return dataModel;
    }

    @Bean(autowire = Autowire.BY_NAME,value = "MySQLJDBCListDataModel")
    public DataModel getMySQLJDBCListDataModel(){
        DataModel dataModel = new MySQLJDBCDataModel(getDataSource(), "listcollection", "CollectUId", "CollectLId", "score", "collect_time");
        return dataModel;
    }


}
