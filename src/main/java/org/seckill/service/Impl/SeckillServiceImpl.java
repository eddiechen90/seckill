package org.seckill.service.Impl;

import org.seckill.Exception.RepeatKillException;
import org.seckill.Exception.SeckillCloseException;
import org.seckill.Exception.SeckillException;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.MD5Util;

import java.util.Date;
import java.util.List;

/**
 * Created by Eddie on 2017/6/3.
 */
@Service("seckillService")
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public Seckill queryById(long id) {
        return seckillDao.queryById(id);
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 100);
    }

    @Override
    public Exposer exportSeckillUrl(long seckkillId) {
        Seckill seckill = seckillDao.queryById(seckkillId);
        if (seckill == null) {
            return new Exposer(false, seckkillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckkillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = MD5Util.MD5EncodeUtf8(Long.toString(seckkillId));
        return new Exposer(true, md5, seckkillId);
    }

    @Override
    @Transactional
    /**
     * 使用注解控制食物方法的优点：
     * 1、开发团队达成一致约定，明确标注事务方法的编程风格。
     * 2、保证事务方法的执行时间尽可能短，不要穿插其他的网络操作。RPC/HTTP请求请求或者剥离到请求方法外部。
     * 3、不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制。
     */
    public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(MD5Util.MD5EncodeUtf8(Long.toString(seckillId)))) {
            throw new SeckillException("seckKill Data rewrited");
        }
        try {
            int rowCount = seckillDao.reduceNumber(seckillId, new Date());
            if (rowCount <= 0) {
                throw new SeckillCloseException("Seckill is closed");
            } else {
                int intsertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (intsertCount <= 0) {
                    throw new RepeatKillException("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new SeckillException("sekill inner error:" + e.getMessage());
        }
    }
}
