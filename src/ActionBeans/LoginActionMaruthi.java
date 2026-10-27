package ActionBeans;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.io.IOException;
import FormBeans.LoginFormMaruthi;
import DataBeans.LoginData;
import nl.captcha.Captcha;

public class LoginActionMaruthi extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String target = null;
        System.out.println("in login Action1111");
        try {
            LoginFormMaruthi loginform = (LoginFormMaruthi) form;
            
            //System.out.println("From LoginAction :"+loginform.getUserid());
            //target = new LoginData().valid(in.amitech.db.DBUtil.getDataSource(), loginform.getUserid(), loginform.getPassword(), request);
            if (loginform.isLoginStatus()) {
                target = "success";
            } else {
                target = "fail";
            }//end else
            System.out.println("Target val from LoginAction :" + target);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            target = "failure";
        }//end catch

        return mapping.findForward(target);
    }//end execute
}//end class
