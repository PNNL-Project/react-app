package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
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
        List<Vav> range = mapper.getVav1InRange("'2018-11-30 06:00:00'", "'2018-11-30 17:50:00'", "vav100","vav100_thresholds");
        for (Vav vav:range){
            System.out.println(vav);
        }
        range = mapper.getVav3InRange("'2018-11-30 06:00:00'", "'2018-11-30 17:50:00'", "vav102","vav100_thresholds");
        for (Vav vav:range){
            System.out.println(vav);
        }
    }
}
