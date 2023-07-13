package com.relaper.cr.springrbac.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.relaper.cr.springrbac.dto.MenuDto;
import com.relaper.cr.springrbac.dto.MenuIndexDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public class TreeUtil {
    //todo 判断list是否为空

    /**
     *
     * @param listByRoleId 通过角色id查询的menuid
     * @param menuDtos 返回的menutree
     * @return
     */
    public static List<MenuDto> tree(List<MenuDto> listByRoleId, List<MenuDto> menuDtos ){
        // if (listByRoleId == null & listByRoleId.size() ==0){
        //     throw
        // }
        List<Integer> collect = listByRoleId.stream().map(MenuDto::getId).collect(Collectors.toList());
        List<Integer> collect1 = menuDtos.stream().map(MenuDto::getId).collect(Collectors.toList());
        // 遍历list2
        for (Integer item : collect) {
            if (collect1.contains(item)) {
                // 如果存在这个数
                MenuDto menuDto = new MenuDto();
                int i = collect1.indexOf(item);
                menuDto = menuDtos.get(i);
                menuDto.setCheckArr("1");
                menuDtos.set(i,menuDto);
            }
        }
        return menuDtos;
    }

    public static void setMenuTree(Integer parentId, List<MenuIndexDto> menusAll, JSONArray array) {
        for (MenuIndexDto per : menusAll) {
            if (per.getParentId().equals(parentId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (menusAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setMenuTree(per.getId(), menusAll, child);
                }
            }
        }
    }

    public static List<MenuIndexDto> parseMenuTree(List<MenuIndexDto> list){
        List<MenuIndexDto> result = new ArrayList<MenuIndexDto>();
        // 1、获取第一级节点
        for (MenuIndexDto menu : list) {
            if(menu.getParentId() == 0) {
                result.add(menu);
            }
        }
        // 2、递归获取子节点
        for (MenuIndexDto parent : result) {
            parent = recursiveTree(parent, list);
        }
        return result;
    }

    public static MenuIndexDto recursiveTree(MenuIndexDto parent, List<MenuIndexDto> list) {
        List<MenuIndexDto>children = new ArrayList<>();
        for (MenuIndexDto menu : list) {
            if (Objects.equals(parent.getId(), menu.getParentId())) {
                children.add(menu);
            }
            parent.setChildren(children);
        }
        return parent;
    }

}
