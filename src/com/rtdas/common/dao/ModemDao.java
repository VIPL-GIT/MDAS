package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.Modem;

public interface ModemDao {

	public void addModem(Modem modem);
	public void deleteModem(Modem modem);
	public Modem getModem(int modemId);
	public List<Modem> listModems();
}
