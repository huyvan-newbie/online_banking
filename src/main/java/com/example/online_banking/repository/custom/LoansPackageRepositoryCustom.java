package com.example.online_banking.repository.custom;

import com.example.online_banking.model.LoansPackage;
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
public class LoansPackageRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    // manage customer
    public List<LoansPackage> getLoansPackageList(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetLoansPackage(paging, false);

        Query query = entityManager.createNativeQuery(sql, LoansPackage.class);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(paging.getStart())
                .setMaxResults(paging.getLength());
        return query.getResultList();
    }

    private String buildSqlGetLoansPackage(PagingRequest paging, boolean isCount) {
        StringBuilder sqlBuilder = new StringBuilder(" FROM LOANS_PACKAGE");
        sqlBuilder.append(" WHERE 1 = 1 ");
        if (!isCount) {
            Order order = paging.getOrder()
                    .get(0);
            int columnIndex = order.getColumn();
            Column column = paging.getColumns()
                    .get(columnIndex);
            if (column != null) {
                if ("id".equals(column.getData())) {
                    sqlBuilder.append(" ORDER BY loan_package_id").append(" ").append(order.getDir());
                } else {
                    sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
                }
            }
        }
        if (isCount) {
            return "SELECT COUNT(*) " + sqlBuilder.toString();
        } else {
            return "SELECT * " + sqlBuilder.toString();
        }
    }

    public Integer getTotalLoansPackage(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        String sql = buildSqlGetLoansPackage(paging, true);

        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.valueOf(query.getSingleResult().toString());
    }
}
