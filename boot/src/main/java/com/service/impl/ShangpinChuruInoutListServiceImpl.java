package com.service.impl;

import com.utils.StringUtil;
import com.service.DictionaryService;
import com.utils.ClazzDiff;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import com.dao.ShangpinChuruInoutListDao;
import com.entity.ShangpinChuruInoutListEntity;
import com.service.ShangpinChuruInoutListService;
import com.entity.view.ShangpinChuruInoutListView;

/**
 * 出入库详情 服务实现类
 */
@Service("shangpinChuruInoutListService")
@Transactional
public class ShangpinChuruInoutListServiceImpl extends ServiceImpl<ShangpinChuruInoutListDao, ShangpinChuruInoutListEntity> implements ShangpinChuruInoutListService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<ShangpinChuruInoutListView> page =new Query<ShangpinChuruInoutListView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
