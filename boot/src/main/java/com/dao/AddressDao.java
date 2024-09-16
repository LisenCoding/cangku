package com.dao;

import com.entity.AddressEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.AddressView;

/**
 * 收货地址 Dao 接口
 *
 * @author 
 */
public interface AddressDao extends BaseMapper<AddressEntity> {

   List<AddressView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
