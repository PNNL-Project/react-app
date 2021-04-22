package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapperTest;
import edu.neu.cs6510.pnnl.hunting.model.UpdateInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Date;


class UpdateInfoMapperTest {


    private static UpdateInfoMapper mapper;

    @BeforeAll
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UpdateInfoMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UpdateInfoMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UpdateInfoMapper.class, builder.openSession(true));
    }

    @Test
    public void insert() {
        UpdateInfo info = new UpdateInfo();
        info.setVavName("vav199");
        info.setTime(new Date());
        mapper.insert(info);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        UpdateInfo info = new UpdateInfo();
        info.setVavName("vav100");
        info.setTime(new Date());
        mapper.updateByPrimaryKeySelective(info);
    }

    @Test
    public void selectByPrimaryKey(){
        UpdateInfo updateInfo = mapper.selectByPrimaryKey("vav100");
        System.out.println(updateInfo);
    }
}