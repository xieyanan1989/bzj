package com.mzk.bzj.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mzk.bzj.dao.model.BaUserInfo;
import com.mzk.bzj.dao.model.Msg;
import com.mzk.bzj.dao.model.Status;
import com.mzk.bzj.service.TestService;
import com.mzk.bzj.util.rsa.Base64;
import com.mzk.bzj.util.rsa.RSAUtils;

@RestController
public class TestControl {

	@Autowired
	private TestService testService ;

	@RequestMapping(value = "/userCount")
	public BaUserInfo userCount(@RequestParam String userName){
		return testService.userCount(userName);
	}
	
	@RequestMapping(value = "/upload")
	public Status imgUpload(@RequestParam("file")MultipartFile[] files){
		return testService.imgUpload(files);
	}
	
	@RequestMapping(value = "/encrypt",produces = MediaType.APPLICATION_JSON_VALUE)
	public Msg rsa(@RequestParam String encryptData){
		String privateKey ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOAs4KmbWosA6n1aanitKqkWoYaZvzaqPFqQk9ZVt1m8BAlUI0JDRKt4W1hExNCJ90X61oHcYEVDzME1ejDKYKuG4dqyt9klpodH1u5ng8hT7M2i/4iZeZgFzg4S8ckKXICqV/f+F/3BbuGxtx6dQzvAnTfemt6TIyHKxtxrCalNAgMBAAECgYATxIFPE27pgPwsb5Oh7osre95kcxxEYsCNr+AJb2eKlyS3+d5eEnZ0tFlBsFa2mOPgHN1beCZZFUBx7RhIQyBA9ZL0yrFFqyRGljjbVdMk+PAtPA+G9QEmP41e6yZuaLNjuUtuwbc0BRwHeVIdW3XMLW4Fe7rl9zBn4yGxUQA+VQJBAPRv7eSsoxAyTJzLp+F1XYvIANHgWUtiqVFNyCo/bJKCw0U9yLl9XkSPqMGtn+OiceVYZPRJmyIbojhoqjKVpgMCQQDqx5Tuu64Ge9DzRmOY81DGaNWqgRVSmnFl+h3txolWJgCytL08zywqV44bmb6fQ4MldbBUTnEb9caO4xJALjpvAkEAuCCES8g7gNzS1x2V1YCKOdwruezMdrvYHVWBHUnvaCG2JDdZN1iXpP+uOE6obVmVgBRo35d+k1/Lf8BfzIDrrwJBAJYH5EyK8rKB/Qz5NjxPgJbJ94B17MJUn5KjOuzvqmO69Uc96JArc/W67b0FiqML6YrOole9qNpNPQ2iXc9Vu1ECQDtWFSom5yWcaQ9PolNRX1O6WlA3F9QXIG8UqZr5JvaZTWfNCa3e0hLVSf0MMiuaHWvBqGYwTYRews47ooB7JKY=";
		//私钥解密过程  
		Msg msg = new Msg();
        byte[] res = null;
		try {
			res = RSAUtils.decryptByPrivateKey(Base64.decode(encryptData),privateKey);
        String restr=new String(res);  
        System.out.println("解密："+restr);  
        System.out.println("加密--------------------");
        msg.setMessage(restr);
        msg.setStatus("0");
        msg.setEncrypt(Base64.encode(RSAUtils.encryptByPrivateKey((msg.toString()).getBytes(),privateKey))); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return msg;
	}
}
