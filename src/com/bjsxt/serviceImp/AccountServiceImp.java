package com.bjsxt.serviceImp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjsxt.mapper.AccountMapper;
import com.bjsxt.service.AccountService;
@Service
public class AccountServiceImp implements AccountService{
	@Resource
	private AccountMapper accountMapper;
	
	//校验转账账户
	@Override
	public int selAcOutInfoService(int aid, String apwd) {
		return accountMapper.selAcOutInfo(aid, apwd);
	}

	//校验金额
	@Override
	public int selMoneyInfoService(int aid, String apwd, double money) {
		return accountMapper.selMoneyInfo(aid, apwd, money);
	}
	
	//校验入账账户信息
	@Override
	public int selAcInInfoService(int aid, String aname) {
		// TODO Auto-generated method stub
		return accountMapper.selAcInInfo(aid, aname);
	}

	//转账
	@Override
	public int upAcService(double money, int aid) {
		return accountMapper.upAc(money, aid);
	}
	
	

}
