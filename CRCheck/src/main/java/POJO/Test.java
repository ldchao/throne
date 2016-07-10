package POJO;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.type.DbTimestampType;

import java.util.EnumSet;

/**
 * Created by mm on 2016/7/10.
 */
public class Test {

    public static  void  main(String[] args){
    DBconnection dBconnection=new DBconnection();

    }
}
