package cn.bober.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Servlet implementation class StartServlet
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String TOKEN = "newlifefromhere";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		System.out.println("signature=" + signature + ", timestamp= "
				+ "" + timestamp + ", nonce=" + nonce + ", echostr=" + echostr);
		
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str);
		String sortStr = str[0] + str[1] + str[2];
		System.out.println("sortStr=" + sortStr);
		String digest = DigestUtils.sha1Hex(sortStr);
		System.out.println("digest=" + digest);
		if(digest.equals(signature)) {
			response.getWriter().print(echostr);
			System.out.println("返回" + echostr + "!");
		} else {
			System.out.println("未返回" + echostr + "!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
