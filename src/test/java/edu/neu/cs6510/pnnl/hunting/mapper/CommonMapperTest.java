package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Common;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonMapperTest {
    private static CommonMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(CommonMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/CommonMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(CommonMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByTime() throws FileNotFoundException, ParseException {

        Timestamp start = getTimestamp("2018-11-30 17:09:00");
        Timestamp end = getTimestamp("2018-11-30 17:19:00");
        List<Common> commons = mapper.selectByTime(start, end);
        for (Common common : commons) {
            System.out.println(common);
        }
    }

    private Timestamp getTimestamp(String timeStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(timeStr);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(format.format(date));
    }

    @Test
    public void testGetVavTableName() {
        String vavName = "vav101";
        vavName = "'" + vavName + "'";
        String tableName = mapper.getVavTableName(vavName);
        System.out.println(tableName);
    }
}
