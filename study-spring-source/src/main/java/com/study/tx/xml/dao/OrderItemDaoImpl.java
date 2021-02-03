package com.study.tx.xml.dao;

import com.study.tx.entity.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 订单项 DAO 接口实现
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public class OrderItemDaoImpl implements OrderItemDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(OrderItem orderItem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into t_order_item (order_id, user_id, product_id, product_name, product_price, purchase_count, pay_money) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatement = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderItem.getOrderId());
            ps.setLong(2, orderItem.getUserId());
            ps.setLong(3, orderItem.getProductId());
            ps.setString(4, orderItem.getProductName());
            ps.setBigDecimal(5, orderItem.getProductPrice());
            ps.setInt(6, orderItem.getPurchaseCount());
            ps.setBigDecimal(7, orderItem.getPayMoney());
            return ps;
        };
        int insert = jdbcTemplate.update(preparedStatement, keyHolder);
        // 获取最终插入的自增的 id
        long itemId = keyHolder.getKey().longValue();
        orderItem.setItemId(itemId);
        //Object[] param = {orderItem.getOrderId(), orderItem.getUserId(), orderItem.getProductId(), orderItem.getProductName(), orderItem.getProductPrice(), orderItem.getPurchaseCount(), orderItem.getPayMoney()};
        //int insert = jdbcTemplate.update(sql, param);
        return insert;
    }
}
