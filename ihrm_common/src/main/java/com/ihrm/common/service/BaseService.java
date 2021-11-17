package com.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author : HK意境
 * @ClassName : BaseService
 * @date : 2021/11/13 20:36
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class BaseService<T>{

    protected Specification<T> getSpecification(String companyId){
        Specification<T> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //更具企业id 查询
                return criteriaBuilder.equal(root.get("companyId").as(String.class), companyId) ;
            }
        };
        return specification ;

    }

}
