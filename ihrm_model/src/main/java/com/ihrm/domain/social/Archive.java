package com.ihrm.domain.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName : Archive
 * @author : HK意境
 * @date : 2021/11/24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Entity
@Table(name = "ss_archive")
@Proxy(lazy = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archive implements Serializable {

    private static final long serialVersionUID = -7883369133555989567L;

	public Archive(String companyId, String yearMonth) {
		this.companyId = companyId;
		this.yearsMonth = yearMonth;
	}

    @Id
    private String id;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 年月
     */
    private String yearsMonth;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 企业缴费
     */
    private BigDecimal enterprisePayment;
    /**
     * 个人缴费
     */
    private BigDecimal personalPayment;
    /**
     * 合计
     */
    private BigDecimal total;
}
