package com.ihrm.domain.attendance.entity;



import com.ihrm.domain.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 请假配置表
 */
@Proxy(lazy = false)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_leave_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveConfig extends BaseEntity implements Serializable {

  @Id
  private String id;

  private String companyId;

  private String departmentId;

  private String leaveType; //类型

  private Integer isEnable;


}
