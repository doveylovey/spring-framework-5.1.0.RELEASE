package com.study.tx.annotation.dao;

import com.study.tx.entity.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.List;

/**
 * 订单 DAO 接口实现
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
@Repository("annotationOrderDao")
public class AnnotationOrderDaoImpl implements AnnotationOrderDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into t_order (user_id, order_status, pay_type, pay_money, gmt_create, gmt_update) values (?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatement = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getUserId());
            ps.setInt(2, order.getOrderStatus());
            ps.setInt(3, order.getPayType());
            ps.setBigDecimal(4, order.getPayMoney());
            ps.setTimestamp(5, Timestamp.valueOf(order.getGmtCreate()));
            ps.setTimestamp(6, Timestamp.valueOf(order.getGmtUpdate()));
            return ps;
        };
        int insert = jdbcTemplate.update(preparedStatement, keyHolder);
        // 获取最终插入的自增的 id
        long orderId = keyHolder.getKey().longValue();
        order.setOrderId(orderId);
        //Object[] param = {order.getUserId(), order.getOrderStatus(), order.getPayType(), order.getPayMoney(), order.getGmtCreate(), order.getGmtUpdate()};
        //int insert = jdbcTemplate.update(sql, param);
        return insert;
    }

    @Override
    public int update(Order order) {
        String sql = "update t_order set order_status = ?, gmt_update = ? where order_id = ?";
        Object[] param = new Object[]{order.getOrderStatus(), order.getGmtUpdate(), order.getOrderId()};
        int update = jdbcTemplate.update(sql, param);
        return update;
    }

    @Override
    public int delete(Long orderId) {
        String sql = "delete from t_order where order_id = ?";
        int delete = jdbcTemplate.update(sql, orderId);
        return delete;
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        String sql = "select order_id orderId, user_id userId, order_status orderStatus, pay_type payType, pay_money payMoney, gmt_create gmtCreate, gmt_update gmtUpdate from t_order where user_id = ?";
        List<Order> orderList = jdbcTemplate.queryForList(sql, new Object[]{userId}, Order.class);
        return orderList;
    }


    // 以下参考：https://blog.csdn.net/cwr452829537/article/details/84955716
    // jdbcTemplate.queryForObject() 方法如果查询结果为空或者不唯一的时候会抛出异常，可以 try...catch 起来返回 null 或者抛出空异常

    @Override
    public Integer userOrderNum(Long userId) {
        // 查询基本数据类型(String、Integer、Long 等)
        // public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType)：参数分别是 sql、sql 参数数组、返回值的数据类型
        String sql = "select COUNT(*) from t_order where user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count;
    }

    @Override
    public List<Integer> userOrderIds(Long userId) {
        // 查询列表
        // public <T> List<T> queryForList(String sql, Object[] args, Class<T> elementType)：参数分别是 sql、sql 参数数组、返回值的数据类型
        String sql = "select order_id orderId from t_order where user_id = ?";
        List<Integer> orderIds = jdbcTemplate.queryForList(sql, new Object[]{userId}, Integer.class);
        return orderIds;
    }

    @Override
    public Order findOne(Long orderId) {
        // 查询单个对象
        // public <T> T queryForObject(String sql, @Nullable Object[] args, RowMapper<T> rowMapper)：参数分别是 sql、sql 参数数组(可为空)，对象映射关系
        ZoneId zoneId = ZoneId.systemDefault();
        String sql = "select order_id, user_id, order_status, pay_type, pay_money, gmt_create, gmt_update from t_order where order_id = ?";
        Order order = jdbcTemplate.queryForObject(sql.toString(), new Object[]{orderId}, (rs, rowNum) -> {
            Order temp = new Order();
            temp.setOrderId(rs.getLong("order_id"));
            temp.setUserId(rs.getLong("user_id"));
            temp.setOrderStatus(rs.getInt("order_status"));
            temp.setPayType(rs.getInt("pay_type"));
            temp.setPayMoney(rs.getBigDecimal("pay_money"));
            temp.setGmtCreate(rs.getDate("gmt_create").toInstant().atZone(zoneId).toLocalDateTime());
            temp.setGmtUpdate(rs.getDate("gmt_update").toInstant().atZone(zoneId).toLocalDateTime());
            return temp;
        });
        return order;
    }

    @Override
    public List<Order> findPage(Long userId, Integer pageNum, Integer pageSize) {
        // 查询列表(含分页)
        // public <T> List<T> query(String sql, @Nullable Object[] args, RowMapper<T> rowMapper)：参数分别是sql，sql参数数组(可为空)，对象映射关系
        String sql = "select order_id orderId, user_id userId, order_status orderStatus, pay_type payType, pay_money payMoney, gmt_create gmtCreate, gmt_update gmtUpdate from t_order where user_id = ? limit ?, ?";
        List<Order> list = jdbcTemplate.query(sql, new Object[]{userId, pageNum, pageSize}, new BeanPropertyRowMapper<>(Order.class));
        return list;
    }
}
