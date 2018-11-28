package com.mzk.bzj.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mzk.bzj.dao.mapper.BaUserInfoMapper;
import com.mzk.bzj.dao.model.BaUserInfo;
import com.mzk.bzj.dao.model.Msg;
import com.mzk.bzj.dao.model.Status;
import com.mzk.bzj.service.TestService;
import com.mzk.bzj.util.log4j.LogUtil;
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private BaUserInfoMapper baUserInfoMapper;
	@Autowired
	private Environment env;
	@Override
	//不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Transactional(rollbackFor={Exception.class})
	public BaUserInfo userCount(String userName) {
		// TODO Auto-generated method stub
		return baUserInfoMapper.selectByPrimaryKey(userName);
	}
	@Override
	public Status imgUpload(MultipartFile[] files) {
		// TODO Auto-generated method stub
		Status status = new Status();
		List<Msg> msgs = new ArrayList<Msg>();
		for(MultipartFile file:files){
			try {
				Msg msg =  new Msg();
				if(file.isEmpty()){
					msg.setMessage("图片为空");
					msg.setStatus("1");
					msgs.add(msg);
					LogUtil.info(new Object[] { "图片上传:图片为空"});
					continue;
				}
				if(file.getSize() > Long.parseLong(env.getProperty("image.maxSize"))){
					msg.setMessage("图片超过10M");
					msg.setStatus("1");
					msgs.add(msg);
					LogUtil.info(new Object[] { "图片上传:图片超过10M"});
					continue;
				}
				if(!judgeImg(file.getOriginalFilename())){
					msg.setMessage("图片格式不正确");
					msg.setStatus("1");
					msgs.add(msg);
					LogUtil.info(new Object[] { "图片上传:图片格式不正确"});
					continue;
				}
				msg.setMessage("成功");
				msg.setStatus("0");
				msgs.add(msg);
				file.transferTo(new File(env.getProperty("image.temp")+file.getOriginalFilename()));
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error(new Object[] {e, "图片上传:异常"});
			}
		}
		status.setResult("0");
		status.setList(msgs);
		return status;
	}
	private boolean judgeImg(String originalFilename) {
		// TODO Auto-generated method stub
		String[] allowedExt = env.getProperty("image.allowedExt").split("\\,");
		String endImg = (originalFilename.split("\\.")[1]).toLowerCase();
		for(String allow : allowedExt){
			if(endImg.equals(allow)){
				return true;
			}
		}
		return false;
	}

}
