package com.dao;

import com.entity.ShangpinOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangpinOrderView;

/**
 * 商品订单 Dao 接口
 *
 * @author 
 */
public interface ShangpinOrderDao extends BaseMapper<ShangpinOrderEntity> {

   List<ShangpinOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
