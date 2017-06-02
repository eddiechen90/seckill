package org.seckill.dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Eddie on 2017/6/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {
        long id=1000;
        testQueryById();
        seckillDao.reduceNumber(id,new Date());
        testQueryById();

    }

    @Test
    public void testQueryById() throws Exception {

        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);

    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckillList=seckillDao.queryAll(0,4);
        for(Seckill seckill:seckillList){
            System.out.println(seckill);
        }
    }
}