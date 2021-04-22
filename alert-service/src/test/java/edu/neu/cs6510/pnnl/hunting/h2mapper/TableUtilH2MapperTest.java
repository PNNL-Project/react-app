package edu.neu.cs6510.pnnl.hunting.h2mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TableUtilH2MapperTest {
    private static TableUtilH2Mapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(TableUtilH2MapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/TableUtilH2MapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(TableUtilH2Mapper.class, builder.openSession(true));
    }

    @Test
    public void testCreateNewVavAlertTable() {
        System.out.println(mapper.createNewVavAlertTable("vav100"));
    }

    @Test
    public void testGetAllTable() {
        List<String> allTable = mapper.getAllTable();
        for(String  table:allTable){
            System.out.println(table);
        }
    }
}
