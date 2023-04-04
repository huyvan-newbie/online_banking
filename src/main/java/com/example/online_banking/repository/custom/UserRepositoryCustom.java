package com.example.online_banking.repository.custom;

import com.example.online_banking.model.User;
import com.example.online_banking.rest.model.Column;
import com.example.online_banking.rest.model.Order;
import com.example.online_banking.rest.model.PagingRequest;
import com.example.online_banking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    // manage customer
    public List<User> getUserList(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetUserList(paging, false);
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }

        Query query = entityManager.createNativeQuery(sql, User.class);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(paging.getStart())
                .setMaxResults(paging.getLength());
        return query.getResultList();
    }

    private String buildSqlGetUserList(PagingRequest paging, boolean isCount) {
        StringBuilder sqlBuilder = new StringBuilder(" FROM USER u JOIN USER_ROLE ur");
        sqlBuilder.append(" ON u.id = ur.user_id ");
        sqlBuilder.append(" WHERE u.status in (1,2) and ur.ROLE_NAME = 'ROLE_USER' ");
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
        }
        if (!isCount) {
            Order order = paging.getOrder()
                    .get(0);
            int columnIndex = order.getColumn();
            Column column = paging.getColumns()
                    .get(columnIndex);
            if (column != null) {
                sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
            }
        }
        if (isCount) {
            return "SELECT COUNT(*) " + sqlBuilder.toString();
        } else {
            return "SELECT u.* " + sqlBuilder.toString();
        }
    }

    public Integer getTotalUser(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetUserList(paging, true);
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }

        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.valueOf(query.getSingleResult().toString());
    }

    //TODO: manage admin
    public List<User> getAdminList(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetAdminList(paging, false);
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }

        Query query = entityManager.createNativeQuery(sql, User.class);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(paging.getStart())
                .setMaxResults(paging.getLength());
        return query.getResultList();
    }

    public Integer getTotalAdmin(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetAdminList(paging, true);
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }

        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.valueOf(query.getSingleResult().toString());
    }

    private String buildSqlGetAdminList(PagingRequest paging, boolean isCount) {
        StringBuilder sqlBuilder = new StringBuilder(" FROM USER u JOIN USER_ROLE ur");
        sqlBuilder.append(" ON u.id = ur.user_id ");
        sqlBuilder.append(" WHERE u.status in (1,2) and ur.ROLE_NAME = 'ROLE_ADMIN' ");
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
        }
        if (!isCount) {
            Order order = paging.getOrder()
                    .get(0);
            int columnIndex = order.getColumn();
            Column column = paging.getColumns()
                    .get(columnIndex);
            if (column != null) {
                sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
            }
        }
        if (isCount) {
            return "SELECT COUNT(*) " + sqlBuilder.toString();
        } else {
            return "SELECT u.* " + sqlBuilder.toString();
        }
    }

//    // get admin by id
//    public List<User> getAdminById(PagingRequest paging, Long id) {
//        Map<String, Object> parameter = new HashMap<>();
//        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM USER u JOIN USER_ROLE ur ");
//        sqlBuilder.append(" ON u.id = ur.user_id ");
//        sqlBuilder.append(" WHERE ur.ROLE_NAME = 'ROLE_ADMIN' ");
//        sqlBuilder.append(" AND u.id = id ");
//        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
//            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
//            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
//        }
//        Order order = paging.getOrder()
//                .get(0);
//
//        int columnIndex = order.getColumn();
//        Column column = paging.getColumns()
//                .get(columnIndex);
//        if (column != null) {
//            sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
//        }
//
//        Query query = entityManager.createNativeQuery(sqlBuilder.toString(), User.class);
//        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
//            query.setParameter(entry.getKey(), entry.getValue());
//        }
//        query.setFirstResult(paging.getStart())
//                .setMaxResults(paging.getLength());
//        return query.getResultList();
//    }
//
//    // edit admin
//    public List<User> editAdmin(PagingRequest paging) {
//        Map<String, Object> parameter = new HashMap<>();
//        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM USER u JOIN USER_ROLE ur ");
//        sqlBuilder.append(" ON u.id = ur.user_id ");
//        sqlBuilder.append(" WHERE ur.ROLE_NAME = 'ROLE_ADMIN' ");
//        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
//            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
//            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
//        }
//        Order order = paging.getOrder()
//                .get(0);
//
//        int columnIndex = order.getColumn();
//        Column column = paging.getColumns()
//                .get(columnIndex);
//        if (column != null) {
//            sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
//        }
//
//        Query query = entityManager.createNativeQuery(sqlBuilder.toString(), User.class);
//        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
//            query.setParameter(entry.getKey(), entry.getValue());
//        }
//        query.setFirstResult(paging.getStart())
//                .setMaxResults(paging.getLength());
//        return query.getResultList();
//    }
}
