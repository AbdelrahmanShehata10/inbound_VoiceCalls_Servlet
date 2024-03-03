package newpackage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageReader;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

@SuppressWarnings("serial")
public class IncomingCallServlet extends HttpServlet {

//    private DAO dao;
//    private ServletContext servletContext;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        servletContext = getServletContext();
//        dao = new DAO(servletContext);
//    }
DataBase db=new DataBase();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IncomingCallServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomingCallServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IncomingCallServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomingCallServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

    
   String[] cred= db.getcred();
                 Twilio.init(cred[1], cred[0]);


        // Assuming you have access to the HttpServletRequest object
     String accountSid = request.getParameter("AccountSid");
        String CallSid = request.getParameter("CallSid");
        String sender = request.getParameter("From");
        String to = request.getParameter("To");
        String CallDuration = request.getParameter("CallDuration");

        db.insertToDB(accountSid, CallSid,sender,to, CallDuration);
        System.out.println(CallSid);
        // Insert message into the database
        Say say = new Say.Builder("Hello world!").build();
        VoiceResponse twiml = new VoiceResponse.Builder().say(say).build();

        // Render TwiML as XML
        response.setContentType("text/xml");

        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }


}
