package com.spring.learning.service.impl;

import com.spring.learning.annonation.MultiDataSourceTransactional;
import com.spring.learning.model.BaseBarcodeInfoModel;
import com.spring.learning.model.BaseDataGroupModel;
import com.spring.learning.service.QrcodeAsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * author: yangyk
 * date: 2020/9/24 14:55
 * description:
 */
@Component
@Log4j2
public class QrcodeAsyncServiceImpl implements QrcodeAsyncService {

	@Resource
	private com.spring.learning.dao.cosmo.BaseBarcodeInfoDao baseBarcodeInfoDao;

	@Resource
	private com.spring.learning.dao.qrcode.BaseDataGroupDao baseDataGroupDao;

	@Async("asyncServiceExecutor")
	@MultiDataSourceTransactional(value = {"qrcodeTransactionManager", "cosmoTransactionManager"})
	@Override
	public void createQrcodeAsync() {
		BaseBarcodeInfoModel model = new BaseBarcodeInfoModel();
		model.setCreateTime(new Date());
		model.setUpdateUserCode("test");
		model.setUpdateTime(new Date());
		model.setSpare1("test");
		baseBarcodeInfoDao.insert(model);
		BaseDataGroupModel baseDataGroupModel = new BaseDataGroupModel();
		baseDataGroupModel.setGroupCode("test1");
		baseDataGroupModel.setGroupDesc("test2");
		baseDataGroupDao.insert(baseDataGroupModel);
		throw new RuntimeException();
	}
}
