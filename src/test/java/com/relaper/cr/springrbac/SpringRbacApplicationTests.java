package com.relaper.cr.springrbac;

import com.relaper.cr.springrbac.dto.MenuDto;
import com.relaper.cr.springrbac.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringRbacApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Autowired
    private MenuService menuService;
    @Test
    void contextLoads() {
        List<MenuDto> menuAll = menuService.buildMenuAll();
        MenuDto menuDto = new MenuDto();
        menuDto.setId(0);
        menuDto.setTitle("顶级目录");
        menuAll.add(menuDto);
        System.out.println(menuAll);
    }

}
