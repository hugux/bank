package com.bjsxt.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjsxt.mapper.AccountMapper;

public interface AccountService {
	int selAcOutInfoService(int aid,String apwd);
	
	int selMoneyInfoService(int aid,String apwd,double money);
	
	int selAcInInfoService(int aid,String aname);
	
	int upAcService(double money,int aid);
}
