package org.seckill.controller;

import org.seckill.Exception.RepeatKillException;
import org.seckill.Exception.SeckillCloseException;
import org.seckill.Exception.SeckillException;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Eddie on 2017/6/3.
 */

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    Logger logger = LoggerFactory.getLogger(SeckillController.class);
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //list.jsp+model=ModelAndView
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list", seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST)
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> resutl;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            resutl = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resutl = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return resutl;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    public SeckillResult<SeckillExcution> excute(@PathVariable("seckillId") long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long killPhone) {
        if (killPhone == null) {
            return new SeckillResult<SeckillExcution>(false, "未注册");
        }
        try {
            SeckillExcution execution = seckillService.excuteSeckill(seckillId, killPhone, md5);
            return new SeckillResult<SeckillExcution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExcution>(false, execution);
        } catch (SeckillCloseException e) {
            SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExcution>(false, execution);
        } catch (Exception e) {
            SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(false, execution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        return new SeckillResult<Long>(true, new Date().getTime());
    }
}
