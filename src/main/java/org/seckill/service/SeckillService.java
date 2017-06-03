package org.seckill.service;

import org.seckill.Exception.RepeatKillException;
import org.seckill.Exception.SeckillCloseException;
import org.seckill.Exception.SeckillException;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;

import java.util.List;

/**
 * Created by Eddie on 2017/6/3.
 */
public interface SeckillService {

    /**
     * 查询单个秒杀记录
     * @param id
     * @return
     */
    Seckill queryById(long id);

    /**
     * 查询所有秒杀产品记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 秒杀开启时输出秒杀接口地址
     * @param seckkillId
     */
    Exposer exportSeckillUrl(long seckkillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException,RepeatKillException,SeckillCloseException;

}
