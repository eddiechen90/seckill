package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Eddie on 2017/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SeckillServiceTest {

    private final Logger logger= LoggerFactory.getLogger(SeckillServiceTest.class);

    @Autowired
    private SeckillService seckillService;
    @Test
    public void testQueryById() throws Exception {
        long id=1000;
        System.out.println(seckillService.queryById(id));

    }

    @Test
    public void testQueryAll() throws Exception {
        System.out.println(seckillService.getSeckillList());
    }

    @Test
    public void testSeckillLogic() throws Exception {
        long id=1009;
        long phone=18710107609L;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        logger.info("exposer:{}",exposer);
        if(exposer.isExposed()==true){
            SeckillExcution excution=seckillService.excuteSeckill(id,phone,exposer.getMd5());
            logger.info("excution:{}",excution);
        }

        //秒杀未开启
        logger.warn("exposer:{}",exposer);

    }

}