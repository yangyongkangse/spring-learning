package com.spring.learning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.learning.dao.qrcode.SysLogDao;
import com.spring.learning.model.SysLogModel;
import com.spring.learning.service.SysLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author: yangyk
 * @date: 2020-07-09
 * @description:
 */
@Service
@Log4j2
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogModel> implements SysLogService {

}
