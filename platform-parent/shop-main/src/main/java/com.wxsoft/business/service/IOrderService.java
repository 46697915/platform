package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Order;
import com.wxsoft.business.pojo.User;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    public long findCount(Order order);

    public List<Order> findAll(PageHelper page, Order order);

    public String add(Order order);

    public String modify(Order order);

    public String delete(Order order);

    /**
     * 订单结算
     * @param goodsDetl
     * @param totalMoney
     * @param totalMoney2
     * @param user
     */
    public String charge(List<Map<String, String>> goodsDetl,
                         String totalMoney, String totalMoney2, User user);
}
