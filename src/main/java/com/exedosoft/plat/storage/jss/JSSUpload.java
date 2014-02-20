package com.exedosoft.plat.storage.jss;

import java.util.List;

import com.jcloud.jss.Credential;
import com.jcloud.jss.JingdongStorageService;
import com.jcloud.jss.client.ClientConfig;
import com.jcloud.jss.domain.Bucket;

public class JSSUpload {
	
//	accessKey： b64a90cc0d654df8a766390517ca77d7
//	secretKey： 636b9a9e811144f29d5a2fe15d5e088fFfQzX6AC
	
	public  static void main(String[] args){

		Credential credential = new Credential("b64a90cc0d654df8a766390517ca77d7",
				"636b9a9e811144f29d5a2fe15d5e088fFfQzX6AC");
		
		JingdongStorageService jss = new JingdongStorageService(credential);

		///如果你是京东内部的用户，需要接入私有云云存储，则需要改变 endpoint
//		ClientConfig config = new ClientConfig(); 
//		
//		config.setEndpoint("storage.jd.com");
//		
//		JingdongStorageService jss = new JingdongStorageService(credential,config);
		
		
		List<Bucket> buckets = jss.listBucket();
		for(Bucket bucket:buckets) {
		System.out.println("bucketName:"+bucket.getName());
		}
		
		jss.destroy();
	}

}
