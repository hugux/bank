package com.bjsxt.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {
	//У��ת���˻���Ϣ
	@Select("select count(*) from account where aid = #{param1} and apwd = #{param2}")
	int selAcOutInfo(int aid,String apwd);
	//У����
	@Select("select count(*) from account where aid = #{param1} and apwd = #{param2} and money > #{param3}")
	int selMoneyInfo(int aid,String apwd,double money);
	//У�������˻���Ϣ
	@Select("select count(*) from account where aid = #{param1} and aname = #{param2}")
	int selAcInInfo(int aid,String aname);
	//ת��
	@Update("update account set  money = money + #{0} where aid = #{1}")
	int upAc(double money,int aid);
}
