package com.exedosoft.plat.storage.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.ObjectMetadata;

/**
 * 该示例代码展示了如果在OSS中创建和删除一个Bucket，以及如何上传和下载一个文件。
 * 
 * 该示例代码的执行过程是：
 * 1. 检查指定的Bucket是否存在，如果不存在则创建它；
 * 2. 上传一个文件到OSS；
 * 3. 下载这个文件到本地；
 * 4. 清理测试资源：删除Bucket及其中的所有Objects。
 * 
 * 尝试运行这段示例代码时需要注意：
 * 1. 为了展示在删除Bucket时除了需要删除其中的Objects,
 *    示例代码最后为删除掉指定的Bucket，因为不要使用您的已经有资源的Bucket进行测试！
 * 2. 请使用您的API授权密钥填充ACCESS_ID和ACCESS_KEY常量；
 * 3. 需要准确上传用的测试文件，并修改常量uploadFilePath为测试文件的路径；
 *    修改常量downloadFilePath为下载文件的路径。
 * 4. 该程序仅为示例代码，仅供参考，并不能保证足够健壮。
 *
 */
public class OSSObjectSample {

    private static final String ACCESS_ID = "IhSzDz3fBlI9SPKY";
    private static final String ACCESS_KEY = "yPlMtVZkp0LZq4DdxemTKUPv3S3bUS";
    private static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";

    /**
     * @param args
     */
    public static void main(String[] args)throws Exception {


    	String bucketName = "zhiliaoshudasai";
        String key = "评审资料.zip";

        String uploadFilePath = "E:/work/doc/幼儿园/阿里云参赛/评审资料/评审资料.zip";
  //      String downloadFilePath = "d:/temp/网站首页2.png";

        // 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
        ClientConfiguration config = new ClientConfiguration();
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);

        try {
     
            System.out.println("正在上传...");
            uploadFile(client, bucketName, key, uploadFilePath);
            

            System.out.println("上传成功");
            
//            ///保存到数据中的路径，这个路径可以直接在浏览器中访问
//            String lujing = OSS_ENDPOINT + "/" + bucketName + "/" + key;
//            
//
//            System.out.println("正在下载...");
//            downloadFile(client, bucketName, key, downloadFilePath);
        } finally {
          //  deleteBucket(client, bucketName);
        }
    }





    // 上传文件
    private static void uploadFile(OSSClient client, String bucketName, String key, String filename)
            throws OSSException, ClientException, FileNotFoundException {
        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
       // objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);
        client.putObject(bucketName, key, input, objectMeta);
    }

    // 下载文件
    private static void downloadFile(OSSClient client, String bucketName, String key, String filename)
            throws OSSException, ClientException {
        client.getObject(new GetObjectRequest(bucketName, key),
                new File(filename));
    }
}
