package com.dao;

import com.entity.ChatEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ChatView;

/**
 * 反馈信息 Dao 接口
 *
 * @author 
 */
public interface ChatDao extends BaseMapper<ChatEntity> {

   List<ChatView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
