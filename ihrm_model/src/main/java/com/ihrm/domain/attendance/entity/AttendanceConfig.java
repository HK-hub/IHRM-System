package com.ihrm.domain.attendance.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ihrm.domain.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HK意境
 */
@Proxy(lazy = false)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_attendance_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *考勤配置表
 */
public class AttendanceConfig extends BaseEntity implements Serializable {

  @Id
  private String id;

  private String companyId;

  @NotBlank(message = "部门ID不能为空")
  private String departmentId;

  @NotNull(message = "上午上班时间不能为空")
  private String morningStartTime;

  @NotNull(message = "上午下班时间不能为空")
  private String morningEndTime;
  @NotNull(message = "下午上班时间不能为空")
  private String afternoonStartTime;
  @NotNull(message = "下午下班时间不能为空")
  private String afternoonEndTime;

  private String createBy;


  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
  private Date createDate ;

  private String updateBy ;


  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
  private Date updateDate ;

  private String remarks ;

}
