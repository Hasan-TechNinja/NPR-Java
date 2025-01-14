package xyz.sadiulhakim.npr.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.sadiulhakim.npr.pojo.TableUrlPojo;
import xyz.sadiulhakim.npr.role.RoleService;
import xyz.sadiulhakim.npr.util.AuthenticatedUserUtil;

@Controller
@RequestMapping("/users")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    private final TableUrlPojo table_url = new TableUrlPojo("", "/users", "",
            "/users/create", "/users/page");

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/page")
    public String page(@RequestParam(defaultValue = "0") int page, Model model) {

        model.addAttribute("name", AuthenticatedUserUtil.getName());
        model.addAttribute("picture", AuthenticatedUserUtil.getPicture());
        model.addAttribute("userResult", userService.findAllPaginated(page));
        model.addAttribute("table_url", table_url);

        return "user/user_page";
    }
}
