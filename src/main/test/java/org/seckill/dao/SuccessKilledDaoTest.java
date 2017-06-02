package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Eddie on 2017/6/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id=1000;
        long phone=18710107609L;
        int row=successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("updateCount:"+row);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id=1000;
        System.out.println( successKilledDao.queryByIdWithSeckill(id));

    }
}