package com.ego.item.pojo;

import java.io.Serializable;
import java.util.List;

public class PortalMenu implements Serializable{
	private List<Object> data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

}
