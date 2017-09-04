package com.ntech.forward;

import com.ntech.util.ConfigManager;

public class Constant {
	
	private static ConfigManager sdk = ConfigManager.getInstance();
	public static final String TOKEN = sdk.getParameter("TOKEN");
	public static final String SDK_IP = sdk.getParameter("SDK_IP");
	public static final String PIC = sdk.getParameter("PIC");
	public static final String DOC = sdk.getParameter("DOC");
	public static final String PATH = sdk.getParameter("PATH");
}
