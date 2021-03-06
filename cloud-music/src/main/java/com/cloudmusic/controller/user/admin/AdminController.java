package com.cloudmusic.controller.user.admin;

import com.cloudmusic.entity.PageInfo;
import com.cloudmusic.model.UserModel;
import com.cloudmusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/allUsers")
    public String ManageUser(@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize,
                             Model model){
        List<UserModel> userModels = adminService.findAllUsers();
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, userModels);
        model.addAttribute("pageInfo", pageInfo);
        return "user/admin/userList";
    }

    /**
     * 处理ajax请求
     * 激活或者停用用户
     * @param userId
     * @return boolean valid
     */
    @ResponseBody
    @PostMapping("/activate")
    public boolean activateUser(String userId){
        int id = Integer.parseInt(userId);
        UserModel model = adminService.activateUser(id);
        return model.isValid();
    }

    /**
     * 处理ajax请求
     * 改变用户权限（普通/VIP)
     * @param userId
     * @return String authority
     */
    @ResponseBody
    @PostMapping("/changeAuthority")
    public String changeUserAuthority(String userId){
        int id = Integer.parseInt(userId);
        UserModel model = adminService.changeUserAuthority(id);
        return model.getAuthority();
    }
}
