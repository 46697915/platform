package com.wxsoft.shiro.business.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxsoft.shiro.business.common.Constant;
import com.wxsoft.shiro.business.entity.Permissions;
import com.wxsoft.shiro.business.entity.PermissionsVo;
import com.wxsoft.shiro.business.mapper.PermissionsMapper;
import com.wxsoft.shiro.business.service.IPermissionsService;
import com.wxsoft.shiro.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cl
 * @since 2019-11-12
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    //    @Autowired
//    private PermissionsMapper mapper;
    @Autowired
    private IUserService uerService;

    /**
     * @param vo
     * @return
     */
    public List selectBy(PermissionsVo vo) {
        List r = baseMapper.selectBy(vo);

        return r;
    }

    @Override
    public List<Permissions> selectUserMenu() {

        //系统管理员，拥有最高权限
        if(uerService.isSuperAdmin()){
            return getAllMenuList(null);
        }

        //用户菜单列表（如果没有权限则是空list，而不是null）
        List<Long> menuListByUser = uerService.getMenuListByUser();
        //用户菜单列表
        List<Permissions> allList = getAllMenuList( menuListByUser);

        return allList;
    }

    /**
     * 获取所有菜单列表
     */
    private List<Permissions> getAllMenuList(List<Long> menuListByUser){
        //查询根菜单列表
        List<Permissions> menuList = queryHasByParentId(0L, menuListByUser);
        //递归获取子菜单
        getMenuTreeList(menuList, menuListByUser);

        return menuList;
    }

    public List getMenuTreeList(List<Permissions> menuList, List menuListByUser){
        List<Permissions> subMenuList = new ArrayList<Permissions>();

        for(Permissions entity : menuList){
            //目录
            String ss = Constant.MenuType.CATALOG.getValue();
            if(Constant.MenuType.CATALOG.getValue().equals(entity.getType())){
                entity.setSubList(getMenuTreeList(queryHasByParentId(entity.getId(), menuListByUser), menuListByUser));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 根据父id获取有权限的子权限
     * @param parentId
     * @param menuListByUser
     * @return
     */
    public List queryHasByParentId(long parentId, List menuListByUser){
        //获取根菜单列表
        List<Permissions> menuList = selectByParentId(parentId);
        //如果 是系统管理员 menuListByUser=null，则有所有权限
        if(menuListByUser == null){
            return menuList;
        }

        List<Permissions> userMenuList = new ArrayList<>();
        for(Permissions menu : menuList){
            if(menuListByUser.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据父权限获取子权限
     * @param parentId
     * @return
     */
    public List selectByParentId(long parentId){
        //获取根菜单列表
        List<Permissions> rootMenu = baseMapper.selectByParentId(parentId);

        return rootMenu ;
    }


}
