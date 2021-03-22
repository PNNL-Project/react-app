package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class VavMapperTest {
    private static VavMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(VavMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/VavMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(VavMapper.class, builder.openSession(true));
    }

    @Test
    public void testGetZoneAirFlowInRange() {
        List<Double> range = mapper.getZoneAirFlowInRange("'2018-11-30 17:23:00'", "'2018-12-01 01:18:00'", "vav100");
        System.out.println(range);

    }

    @Test
    public void testGetZoneTemperatureInRange() {
        List<Double> range = mapper.getZoneTemperatureInRange("'2018-11-30 17:23:00'", "'2018-12-01 01:18:00'", "vav100");
        System.out.println(range);
    }

    @Test
    public void testGetVavInRange() {
        List<Vav> range = mapper.getVavInRange("'2018-11-30 17:23:00'", "'2018-12-01 01:18:00'", "vav100");
        for (Vav vav:range){
            System.out.println(vav);
        }
    }
}
