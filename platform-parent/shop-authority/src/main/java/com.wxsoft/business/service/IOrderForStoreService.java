package com.wxsoft.business.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Order;
import com.wxsoft.business.pojo.User;

public interface IOrderForStoreService {
	
	public long findCount(Order order);
	public int getLastId(Order order);
	
	public List<Order> findAll(PageHelper page, Order order);
	
	public String add(Order order);
	
	public String modify(Order order);
	
	public String delete(Order order);

	/**
	 * 订单结算
	 * @param goodsIds
	 * @param countsTemp
	 * @param totalMoney
	 * @param totalMoney2
	 */
	public String charge(List<Map<String, String>> goodsDetl,
                         String totalMoney, String totalMoney2, User user);
}
