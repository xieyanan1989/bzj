package com.mzk.bzj.dao.model;

import java.util.List;

public class Status {

	private String result;
	private List<Msg> list;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Msg> getList() {
		return list;
	}
	public void setList(List<Msg> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "{result=" + result + ", list=" + list + "}";
	}
	
	
}
