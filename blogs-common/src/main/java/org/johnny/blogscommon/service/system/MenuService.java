package org.johnny.blogscommon.service.system;

import org.johnny.blogscommon.vo.requestvo.system.MenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.MenuResultVo;

import java.util.List;

/**
 * 菜单的Service
 *
 * @author johnny
 * @create 2020-07-11 下午4:09
 **/

public interface MenuService {


    void addMenu(MenuReqVo menuReqVo);

    List<MenuResultVo> findAllMenuList(boolean includeButton);

    List<MenuResultVo> findAllMenuListAndButton();

    void editMenu(MenuReqVo menuReqVo);

    void deleteMenu(Long id);
}