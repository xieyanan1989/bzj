package com.mak.bzj;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mzk.bzj.util.rsa.Base64;
import com.mzk.bzj.util.rsa.RSAUtils;

import net.coobird.thumbnailator.Thumbnails;
//@SpringBootTest
@RunWith(SpringRunner.class)
public class BZJTest {

	 @Value("${image.temp.dir}")    
	 private String temp;  
	 @Value("${image.real.dir}")    
	 private String real;  
	 @Value("${image.compress.dir}")    
	 private String compress;  
	 
	 	@Configuration
	    @PropertySource("classpath:application.properties")
	    static class PropertiesWithJavaConfig {
	 
	        @Bean
	        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	            return new PropertySourcesPlaceholderConfigurer();
	        }
	    }
	//图片压缩
	@Test
	public void compressedImage() throws IOException{
		 //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
		String imgName = "IMG_4935.JPG";
        Thumbnails.of(temp+imgName).scale(0.25f).outputQuality(0.25f).toFile(compress+imgName);
        Thumbnails.of(temp+imgName).scale(1f).outputQuality(1f).toFile(real+imgName);
	}
	
	//rsa公钥私钥生成
	@Test
	public void rsaGen() throws IOException{
		String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgLOCpm1qLAOp9Wmp4rSqpFqGGmb82qjxakJPWVbdZvAQJVCNCQ0SreFtYRMTQifdF+taB3GBFQ8zBNXowymCrhuHasrfZJaaHR9buZ4PIU+zNov+ImXmYBc4OEvHJClyAqlf3/hf9wW7hsbcenUM7wJ033prekyMhysbcawmpTQIDAQAB";
		String privateKey ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOAs4KmbWosA6n1aanitKqkWoYaZvzaqPFqQk9ZVt1m8BAlUI0JDRKt4W1hExNCJ90X61oHcYEVDzME1ejDKYKuG4dqyt9klpodH1u5ng8hT7M2i/4iZeZgFzg4S8ckKXICqV/f+F/3BbuGxtx6dQzvAnTfemt6TIyHKxtxrCalNAgMBAAECgYATxIFPE27pgPwsb5Oh7osre95kcxxEYsCNr+AJb2eKlyS3+d5eEnZ0tFlBsFa2mOPgHN1beCZZFUBx7RhIQyBA9ZL0yrFFqyRGljjbVdMk+PAtPA+G9QEmP41e6yZuaLNjuUtuwbc0BRwHeVIdW3XMLW4Fe7rl9zBn4yGxUQA+VQJBAPRv7eSsoxAyTJzLp+F1XYvIANHgWUtiqVFNyCo/bJKCw0U9yLl9XkSPqMGtn+OiceVYZPRJmyIbojhoqjKVpgMCQQDqx5Tuu64Ge9DzRmOY81DGaNWqgRVSmnFl+h3txolWJgCytL08zywqV44bmb6fQ4MldbBUTnEb9caO4xJALjpvAkEAuCCES8g7gNzS1x2V1YCKOdwruezMdrvYHVWBHUnvaCG2JDdZN1iXpP+uOE6obVmVgBRo35d+k1/Lf8BfzIDrrwJBAJYH5EyK8rKB/Qz5NjxPgJbJ94B17MJUn5KjOuzvqmO69Uc96JArc/W67b0FiqML6YrOole9qNpNPQ2iXc9Vu1ECQDtWFSom5yWcaQ9PolNRX1O6WlA3F9QXIG8UqZr5JvaZTWfNCa3e0hLVSf0MMiuaHWvBqGYwTYRews47ooB7JKY=";
		try {
			System.out.println(publicKey.length());
			System.out.println(privateKey.length());
//			RSAUtils.genKeyPair("00000001");
			System.out.println("--------------公钥加密私钥解密过程-------------------");  
	        String plainText="ihep_公钥加密私钥解密";  
	        //公钥加密过程  
	        byte[] cipherData=RSAUtils.encryptByPublicKey(plainText.getBytes(),publicKey);  
	        String cipher=Base64.encode(cipherData);  
	        System.out.println("原文："+plainText);  
	        System.out.println("加密："+cipher);  
	        //私钥解密过程  
	        byte[] res=RSAUtils.decryptByPrivateKey(Base64.decode(cipher),privateKey);  
	        String restr=new String(res);  
	        System.out.println("解密："+restr);  
	        System.out.println();  
	        System.out.println("--------------私钥加密公钥解密过程-------------------");  
	        plainText="ihep_私钥加密公钥解密";  
	        //私钥加密过程  
	        cipherData=RSAUtils.encryptByPrivateKey(plainText.getBytes(),privateKey);  
	        cipher=Base64.encode(cipherData);  
	        //公钥解密过程  
	        res=RSAUtils.decryptByPublicKey(Base64.decode(cipher),publicKey );  
	        restr=new String(res);  
	        System.out.println("原文："+plainText);  
	        System.out.println("加密："+cipher);  
	        System.out.println("解密："+restr);  
	        System.out.println();  
	        
	        System.out.println("---------------私钥签名过程------------------");  
	        String content="ihep_这是用于签名的原始数据";  
	        String signstr=RSAUtils.sign(content.getBytes("utf-8"),privateKey);  
	        System.out.println("签名原串："+content);  
	        System.out.println("签名串："+signstr);  
	        System.out.println();  
	           
	        System.out.println("---------------公钥校验签名------------------");  
	        System.out.println("签名原串："+content);  
	        System.out.println("签名串："+signstr);  
	        System.out.println("验签结果："+RSAUtils.verify(content.getBytes("utf-8"), publicKey,signstr));  
	        System.out.println();  
	        
	        String a = "abd";
	        String b = a;
	        b = "123";
	        System.out.println("bbbbbb:"+b);
	        System.out.println("aaaaaaaaaaa:"+a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
