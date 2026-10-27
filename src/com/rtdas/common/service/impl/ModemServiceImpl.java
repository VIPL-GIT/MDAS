package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.ModemDao;
import com.rtdas.common.models.Modem;
import com.rtdas.common.service.ModemService;

@Service("modemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModemServiceImpl implements ModemService {
	@Autowired
	private ModemDao modemDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addModem(Modem modem) {
		modemDao.addModem(modem);

	}

	@Override
	public void deleteModem(Modem modem) {
		modemDao.deleteModem(modem);

	}

	@Override
	public Modem getModem(int modemId) {
		return modemDao.getModem(modemId);

	}

	@Override
	public List<Modem> listModems() {
		return modemDao.listModems();
	}

}
