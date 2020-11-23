package onlineusers.controller;

import onlineusers.service.OnlineUsers;
import onlineusers.service.OnlineUsersPerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private OnlineUsers onlineUsers;

    @Autowired
    private OnlineUsersPerPage onlineUsersPerPage;

    @GetMapping({"/", "/index"})
    public ModelAndView homePage(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5);

        onlineUsersPerPage.setSessionOnPage("index", session.getId());

        modelAndView.setViewName("index");

        return modelAndView;

    }

    @GetMapping("/testPage")
    public ModelAndView testPage(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5);

        onlineUsersPerPage.setSessionOnPage("testPage", session.getId());

        modelAndView.setViewName("testPage");

        return modelAndView;

    }

    @GetMapping("/onlineusers/{pageName}")
    @ResponseBody
    public int[] onlineUsers(@PathVariable("pageName") String pageName){

        int []arrNum = new int[2];

        arrNum[0] = onlineUsersPerPage.getNumberOnlineOnPage(pageName);
        arrNum[1] = onlineUsers.getNumberOfUsersOnline();

        return arrNum;

    }

    @GetMapping("/remove/{pageName}")
    public void test(@PathVariable("pageName") String pageName, HttpServletRequest request){
        HttpSession session = request.getSession();
        onlineUsersPerPage.removeSessionOnPage(pageName, session.getId());
    }


}
