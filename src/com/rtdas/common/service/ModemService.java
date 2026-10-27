package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.models.Modem;

public interface ModemService {

	public void addModem(Modem modem);
	public void deleteModem(Modem modem);
	public Modem getModem(int modemId);
	public List<Modem> listModems();
}
