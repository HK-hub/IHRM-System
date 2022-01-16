package com.ihrm.domain.attendance.entity;



import com.ihrm.domain.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "atte_archive_monthly")
@Proxy(lazy = false)
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveMonthly extends BaseEntity implements Serializable {

  @Id
  private String id;
  private String companyId;
  private String departmentId;

  private String archiveYear;
  private String archiveMonth;
  private Integer totalPeopleNum;

  private Integer fullAttePeopleNum;
  private Integer isArchived;
}
