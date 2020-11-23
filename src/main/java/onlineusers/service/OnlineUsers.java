package onlineusers.service;

import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
   Class OnlineUsers counts existing sessions on website.
   Class OnlineUsersPerPage counts online users on every page separately.
   It is possible to count total online users on website in the OnlineUsersPerPage class.
   However, OnlineUsers class was added for demonstration purposes only. It implements another logic than
   OnlineUsersPerPage class.
*/
@Service
@WebListener
public class OnlineUsers implements HttpSessionListener {

    Logger logger = Logger.getLogger(OnlineUsers.class.getName());


    private int numberOfUsersOnline;

    public int getNumberOfUsersOnline() {
        return numberOfUsersOnline;
    }

    public void sessionCreated(HttpSessionEvent event) {

        logger.log(Level.INFO, "Session created by Id : " + event.getSession().getId());
//        System.out.println("Session created by Id : " + event.getSession().getId());
        synchronized (this) {
            numberOfUsersOnline++;
        }

    }

    public void sessionDestroyed(HttpSessionEvent event) {

        logger.log(Level.INFO, "Session destroyed by Id : " + event.getSession().getId());
//        System.out.println("Session destroyed by Id : " + event.getSession().getId());
        synchronized (this) {
            numberOfUsersOnline--;
        }

    }

}

