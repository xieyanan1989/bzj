package com.mzk.bzj.service;

import org.springframework.web.multipart.MultipartFile;

import com.mzk.bzj.dao.model.BaUserInfo;
import com.mzk.bzj.dao.model.Status;

public interface TestService {

	BaUserInfo userCount(String userName);

	Status imgUpload(MultipartFile[] files);
}
