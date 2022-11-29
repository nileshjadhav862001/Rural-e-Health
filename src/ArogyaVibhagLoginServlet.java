

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.AccountCredentials;
import com.basic.DbConnection;

/**
 * Servlet implementation class ArogyaVibhagLoginServlet
 */
public class ArogyaVibhagLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArogyaVibhagLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Connection con = DbConnection.connect();
		try 
		{
			PreparedStatement pstmt = con.prepareStatement("select * from arogyavibhag where email = ? and password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
		
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
	            AccountCredentials.setAv_id(rs.getInt(1));
	            System.out.println(rs.getInt(1));
				response.sendRedirect("arogyavibhag_panel.html");
			}
			else
			{
				response.sendRedirect("error_page.html");
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
