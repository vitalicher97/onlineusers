package onlineusers.service;

import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OnlineUsersPerPage {

    Logger logger = Logger.getLogger(OnlineUsersPerPage.class.getName());

    Hashtable<String, LinkedList> pageAndSessions = new Hashtable<>();


    public void setSessionOnPage(String page, String sessionId) {

        if(!pageAndSessions.containsKey(page)){
            LinkedList<String> sessionsIdList = new LinkedList<>();
            pageAndSessions.put(page, sessionsIdList);
            logger.log(Level.INFO, "Added page name : " + page);
        }
        if(!pageAndSessions.get(page).contains(sessionId)){
            pageAndSessions.get(page).add(sessionId);
            logger.log(Level.INFO, "Stored sessionID : " + sessionId + " to page : " + page);
        }

    }

    public int getNumberOnlineOnPage(String page){

//        logger.log(Level.INFO, "Receive size of list that corresponds to page : " + page);
        return pageAndSessions.get(page).size();

    }

    public void removeSessionOnPage(String page, String sessionId){

        boolean status = pageAndSessions.get(page).remove(sessionId);
        logger.log(Level.INFO, "Removed : " + sessionId + " related to page : " + page + ". Is existed : " + status);

    }

    /*public void sessionDestroyed(HttpSessionEvent event) {

        synchronized (this) {
            Set<String> keys = pageAndSessions.keySet();
            for(String key : keys){
                pageAndSessions.get(key).remove(event.getSession().getId());
            }
        }

    }*/


}
