package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.Alert;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class AlertMapperTest {
    private static AlertMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(AlertMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/AlertMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(AlertMapper.class, builder.openSession(true));
    }

    @Test
    public void testGetAllAlert() {
        List<Alert> allAlert = mapper.getAllAlert();
        System.out.println(allAlert);
    }

    @Test
    public void testInsert() {
        Alert alert = new Alert();
        alert.setTime(new Date());
        mapper.insert(alert);
    }
}
