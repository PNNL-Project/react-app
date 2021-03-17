package edu.neu.cs6510.pnnl.hunting.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class TableUtilMapperTest {
    private static TableUtilMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(TableUtilMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/TableUtilMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(TableUtilMapper.class, builder.openSession(true));
    }

    @Test
    public void testExistTable() {
        int i = mapper.existTable("vav101");
        System.out.println(i);
    }


}
