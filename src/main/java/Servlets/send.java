/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;


import Package.DatabaseConnection;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.servlet.http.Part;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Request;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlo
 */
@MultipartConfig
public class send extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws jakarta.mail.MessagingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
        String name = request.getParameter("name");
        String startdate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String startdate2[] = request.getParameterValues("startDate2");
        String endDate2[] = request.getParameterValues("endDate2");
        String leave = request.getParameter("leave");
        String leave2[] = request.getParameterValues("leave2");
        String department = request.getParameter("optradio");
        String comments = request.getParameter("comments");
        
        
        String from = "alphachemical.hr@gmail.com";
        //String to = "fernandes6976@gmail.com";        
        String host = "smtp.gmail.com";
        
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new jakarta.mail.Authenticator() {
          @Override
               protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(from,"cgpj yxvg ufys xeyv"); 
               }
        });
        
        response.setContentType("text/html");
       
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("cfernandes@alphachemical.com"));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress("sjuckett@alphachemical.com"));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress("cfernandes@alphachemical.com"));
            
            message.setSubject("Time Off Request From: "+name);
            StringBuilder sb = new StringBuilder();
            sb.append("Hello,").append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append("This employee requested a Time Off: ").append(System.lineSeparator());
            sb.append("+----------------------------------+").append(System.lineSeparator());
            sb.append("Name: ").append(name).append(System.lineSeparator());         
            sb.append("+----------------------------------+").append(System.lineSeparator());
            sb.append("Departmet: ").append(department).append(System.lineSeparator());
            sb.append("+-----------------------------------------------------+").append(System.lineSeparator());
            sb.append("Type of Leave: ").append(leave).append(System.lineSeparator());
            sb.append("Start Date: ").append(startdate);
            sb.append("  End Date: ").append(endDate).append(" - ").append(days(endDate,startdate)).append(" Day(s)").append(System.lineSeparator());
  
            if(startdate2 != null)
            {
                for(int i=0; i < startdate2.length;i++){
                    sb.append("+-----------------------------------------------------+").append(System.lineSeparator());
                    sb.append("Type of Leave: ").append(leave2[i]).append(System.lineSeparator());
                    sb.append("Start Date: ").append(startdate2[i]);
                    sb.append("  End Date: ").append(endDate2[i]).append(" - ").append(days(endDate2[i],startdate2[i])).append(" Day(s)").append(System.lineSeparator());
                }
            }
            sb.append("+-----------------------------------------------------+").append(System.lineSeparator());
            sb.append("Comments: ").append(System.lineSeparator());
            sb.append(comments).append(System.lineSeparator());
        
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(sb.toString());
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        //File tempFile = null;
        //Part filePart = request.getPart("myfile");
        //String fileName = filePart.getSubmittedFileName();
        //if(!fileName.equals(""))
       // {

        // Save the file temporarily
        //tempFile = File.createTempFile("upload",fileName);

        //try (InputStream fileContent = filePart.getInputStream();
         //    FileOutputStream fos = new FileOutputStream(tempFile)) {
          //  byte[] buffer = new byte[1024];
          //  int bytesRead;
           // while ((bytesRead = fileContent.read(buffer)) != -1) {
            //    fos.write(buffer, 0, bytesRead);
           // }
       // }

         //MimeBodyPart attachmentPart = new MimeBodyPart();
         //attachmentPart.attachFile(tempFile);
         //attachmentPart.setFileName(fileName);
         //multipart.addBodyPart(attachmentPart);
        
        //}
 
         // Send the complete message parts
          message.setContent(multipart );
          
          Transport.send(message);
          //if(tempFile!=null)
          //tempFile.delete();
          
        SimpleDateFormat ft  = new SimpleDateFormat("yyyy-MM-dd");
        String date = ft.format(new Date());
        int id=0;
        try {
            Connection con = (Connection) DatabaseConnection.initializeDatabase();
            Statement  st = con.createStatement();
            //Statement  st2 = con.createStatement();
            String sql = "insert into vacation.forms(empName,reqDate,departament,obs) values('"+name+"','"+date+"','"+department+"','"+comments+"')";
            st.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet res = st.getGeneratedKeys();
            while(res.next()){
                id = res.getInt(1);
                 
            }
            if(id!=0){
                String sql2 = "insert into vacation.dates(formsId,inDate,endDate,type) values("+id+",'"+startdate+"','"+endDate+"','"+leave+"')";
                st.execute(sql2);
              if(startdate2 != null)
                {
                    for(int i=0; i < startdate2.length;i++)
                    {
                    
                    String sql3 = "insert into vacation.dates(formsId,inDate,endDate,type) values("+id+",'"+startdate2[i]+"','"+endDate2[i]+"','"+leave2[i]+"')";
                    st.execute(sql3);
                    }
                }
            }
            
            con.close();
            st.close();           
                       
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(send.class.getName()).log(Level.SEVERE, null, ex);
            }
     
         response.sendRedirect("confirmation.html");
        } catch (AddressException | ParseException ex) {
            Logger.getLogger(send.class.getName()).log(Level.SEVERE, null, ex);
        }
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public int days (String date1, String date2) throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateI = dateFormat.parse(date1);
        Date dateF =dateFormat.parse(date2);
        long difference_In_Time = dateI.getTime() - dateF.getTime();
        long difference_In_Days
                = (difference_In_Time
                   / (1000 * 60 * 60 * 24))
                  % 365;
        
        int days = (int) difference_In_Days;
        if(days>=7)
        return days-2;

        else if(days>=14)
        return days-4;

        else if(days>=21)
        return days-6;
       
        return days;
        
    }
}
