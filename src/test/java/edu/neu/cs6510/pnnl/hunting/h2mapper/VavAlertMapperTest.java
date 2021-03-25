package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.VavAlert;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class VavAlertMapperTest {
    private static VavAlertMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(VavAlertMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/VavAlertMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(VavAlertMapper.class, builder.openSession(true));
    }

    @Test
    public void testGetVavAlertInRange() {
        List<VavAlert> range = mapper.getVavAlertInRange("'2021-03-24 22:00:00'", "'2021-03-24 22:10:00'");
        System.out.println(range);
    }
}
