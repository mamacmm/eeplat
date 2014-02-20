package com.exedosoft.plat.login;

import java.util.TimerTask;

import com.exedosoft.plat.bo.DOService;

public class CheckOnlineTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		OnlineManager.checkSessionOnline();

	}

	
	public static void main(String[] args){
		
		DOService aService = DOService.getService("test_t1_auto_criteria_2");
		
		aService.invokeSelect("111","\"2222\"");
		
		
	}
}
