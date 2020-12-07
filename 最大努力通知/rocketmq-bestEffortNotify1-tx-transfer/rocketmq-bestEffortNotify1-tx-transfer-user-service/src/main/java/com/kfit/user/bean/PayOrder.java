package com.kfit.user.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author 悟纤「公众号SpringBoot」
 * @date 2020-11-25
 * @slogan 大道至简 悟在天成
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class PayOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long id;//主键id
	private long uid;//支付用户.
	private BigDecimal payAmount;//充值余额
	private String result;//充值结果:success，fail
	private Date createTime;//创建时间.
	@Column(unique = true)
	private String txNO;
	
	
	public String getTxNO() {
		return txNO;
	}
	public void setTxNO(String txNO) {
		this.txNO = txNO;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
