package com.xxx.oa.cfg;

public class Configuration {
	private static int pageSize;
	
	// get attribute from default.properties and init all attributes	
	static {
		pageSize=10;
	}

	public static int getPageSize() {
		return pageSize;
	}
	

}
