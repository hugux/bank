package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bjsxt.service.AccountService;
@WebServlet("/bank")
public class AccountServlet extends HttpServlet{
	//声明业务层对象
	private AccountService as;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean("accountService",AccountService.class);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//获取操作符信息
		String oper = req.getParameter("oper");
		//判断
		if("acout".equals(oper)) {
			checkAcOut(req,resp);
		}else if("money".equals(oper)) {
			checkAcMoney(req,resp);
		}else if("acin".equals(oper)) {
			checkAcIn(req,resp);
		}else if("transfer".equals(oper)) {
			checktransfer(req,resp);
		}else {
			System.out.println("没有找到相应的操作符："+oper);
		}
	}

	//转账
	private void checktransfer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int acout = Integer.parseInt(req.getParameter("outid"));
		int acin = Integer.parseInt(req.getParameter("inid"));
		double money = Double.parseDouble(req.getParameter("money"));
		//入账
		int in = as.upAcService(money, acin);
		//转出
		money = -money;
		int out = as.upAcService(money, acout);
		int i = in + out;
		if(i == 2) {
			resp.getWriter().write("2");
			System.out.println("转账成功！！！");
		}
		
	
	}
	//校验转账账户信息
	private void checkAcOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int aid = Integer.parseInt(req.getParameter("aid"));
		String apwd = req.getParameter("apwd");
		int a = as.selAcOutInfoService(aid, apwd);
		if (a > 0) {
			resp.getWriter().write("1");
		}else {
			resp.getWriter().write("0");
		}
	}
	
	//校验金额
	private void checkAcMoney(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int aid = Integer.parseInt(req.getParameter("aid"));
		String apwd = req.getParameter("apwd");
		double money = Double.parseDouble(req.getParameter("money"));
		int m = as.selMoneyInfoService(aid, apwd, money);
		if(m > 0) {
			resp.getWriter().write("1");
		}else {
			resp.getWriter().write("0");
		}
	}
	
	//校验入账账户信息
	private void checkAcIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int aid = Integer.parseInt(req.getParameter("inid"));
		int aid2 = Integer.parseInt(req.getParameter("aid"));
		String aname = req.getParameter("aname");
		int i;
		if(aid == aid2) {
			i = -1;
		}else {
			i = as.selAcInInfoService(aid, aname);
		}
		if(i > 0) {
			resp.getWriter().write("1");
		}else {
			resp.getWriter().write("0");
		}
	}
	
}
