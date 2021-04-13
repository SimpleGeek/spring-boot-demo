package com.simplegeek.springbootdemo.orders;

import java.math.BigDecimal;

public class OrderItem {
	private int lineNum;
	private long skuId;
	private BigDecimal amt;
	private int qty;
	
	public OrderItem(int lineNum, long skuId, BigDecimal amt, int qty) {
		this.lineNum = lineNum;
		this.skuId = skuId;
		this.amt = amt;
		this.qty = qty;
	}
	
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public long getSkuId() {
		return skuId;
	}
	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
