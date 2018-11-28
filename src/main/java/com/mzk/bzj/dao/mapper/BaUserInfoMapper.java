package com.mzk.bzj.dao.mapper;

import org.springframework.stereotype.Repository;

import com.mzk.bzj.dao.model.BaUserInfo;
@Repository
public interface BaUserInfoMapper {
    int deleteByPrimaryKey(String userName);

    int insert(BaUserInfo record);

    int insertSelective(BaUserInfo record);

    BaUserInfo selectByPrimaryKey(String userName);

    int updateByPrimaryKeySelective(BaUserInfo record);

    int updateByPrimaryKey(BaUserInfo record);
}