package action.library;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.ActionForward;
import model.Book;
import model.BookDao;
import model.Member;
import model.MemberDao;

public class AllAction {
	protected String login;  
	protected String id;
	 
	private boolean logincheck(HttpServletRequest request) {
		Member login = (Member)request.getSession().getAttribute("login");
		if(login == null) {
			request.setAttribute("msg", "�α��� �� �ŷ��ϼ���");
			request.setAttribute("url", "../member/main.do");
			return false;
		}
		return true;
	}
	
	public ActionForward info(HttpServletRequest request, HttpServletResponse response) throws Exception {
		id = request.getParameter("id");
		if(logincheck(request)) {
			Member mem = new MemberDao().selectOne(id);
			request.setAttribute("mem", mem); 
			request.setAttribute("list", new MemberDao().list());
			return new ActionForward();
		}else {
			return new ActionForward(false,"../alert.jsp");
		}			
	}
	
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return new ActionForward(true, "main.do");	
	}
	
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "���̵� Ȯ���ϼ���";
		String url = "main.do";
		//1. �Ķ���� ����
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//2. db ���� �б�.
		Member mem = new MemberDao().selectOne(id);
		//3. ���̵�, ��й�ȣ ����
		if(mem != null){
			if(pass.equals(mem.getPass())){
				request.getSession().setAttribute("login", mem);
				request.getSession().setAttribute("login2", id);
				msg = mem.getName() + "���� �α��� �߽��ϴ�.";
				url = "main.do";
			}else{
				msg = "��й�ȣ�� Ʋ���ϴ�.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		String msg = "ȸ������ ����";  
		String url = "joinForm.do"; 
		MemberDao dao = new MemberDao();	//model Ŭ����
		int result = dao.insert(mem);
		if(result > 0){
			msg = mem.getName() + "�� ȸ�� ���� �Ϸ�";
			url = "main.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}	

	public ActionForward picture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getServletContext().getRealPath("/") + "project/member/picture/";
		String fname = null;
		try{
			File f = new File(path);
			if(!f.exists()){
				f.mkdirs();	//������������ ���� ����
			}
			MultipartRequest multi = new MultipartRequest
					(request, path, 10*1024*1024, "euc-kr");
			//fname: ���ε�� ���� �̸�
			fname = multi.getFilesystemName("picture");
			//����� �̹��� ����
			//new File(path + fname): ���ε�� ���� ����
			//bi: �޸𸮿� �ε� ����
			BufferedImage bi = ImageIO.read(new File(path + fname));
			int width = bi.getWidth()/3;	
			int height = bi.getHeight()/3;
			BufferedImage thumb = new BufferedImage
					(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi,0,0,width,height,null);
			f = new File(path + "sm_" + fname);
			ImageIO.write(thumb,"jpg",f);	
		}catch(IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("fname", fname);
		return new ActionForward();
	}
	
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));

		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm.do?id=" + mem.getId();
		MemberDao dao = new MemberDao();
		Member dbmem = dao.selectOne(mem.getId());
		String login = (String)request.getSession().getAttribute("login2");
		
		if(login.equals("admin") || mem.getPass().equals(dbmem.getPass())){
			int result = dao.update(mem);
			if(result > 0){
				return new ActionForward(true, "info.do?id=" + mem.getId());
			}else{
				msg = "��������";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pass = request.getParameter("pass");
		String msg = null;
		String url = null;
		String login = (String)request.getSession().getAttribute("login2");
		String id = request.getParameter("id");
		
		if(id.equals("admin")){
			msg = "�����ڴ� Ż���� �� �����ϴ�.";
			url = "info.do";
		}else {
			MemberDao dao = new MemberDao();
			Member dbmem = dao.selectOne(id);
			
			if(login.equals("admin") || pass.equals(dbmem.getPass())){
				if(dao.delete(id) > 0){	//��������
					if(login.equals("admin")){	//�������ΰ��
						msg = id + "����ڸ� ���� Ż�� ����";
						url = "mlist.do"; 
					} else{	//�Ϲݻ������ ���
						msg = id + "���� ȸ�� Ż�� �Ϸ� �Ǿ����ϴ�.";
						url = "main.do";
						request.getSession().invalidate();	//�α׾ƿ�
					}
				}else { //���� ����
					msg = id + "���� Ż��� ���� �߻�";
					if(login.equals("admin")){	//�������� ���
						url = "info.do";
					}else{	//�Ϲ� ������ΰ��
						url = "info.do?id=" + id;
					}
				}
			}else {	//�Ϲݻ���ڰ� ��й�ȣ�� Ʋ�����
				msg = id + "���� ��й�ȣ�� Ʋ���ϴ�.";
				url = "deleteForm.do?id=" + id;
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);	
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward passfind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean opener = false;
		boolean closer = false;
		String msg = null;
		String url = null;
				
		String id = (String)request.getSession().getAttribute("login2");
		if(id == null){
			opener = true;
			closer = true;
			msg = "�α��� �ϼ���.";
			url = "main.do";
		}else{	//�α��λ���
			String pass = request.getParameter("pass");	//������ ��й�ȣ
			String chgpass = request.getParameter("chgpass");//������ ��й�ȣ
			MemberDao dao = new MemberDao();
			Member mem = dao.selectOne(id);
			//mem.getPass(): db�� ����� ��й�ȣ
			if(pass.equals(mem.getPass())){	//�Էµ� ��й�ȣ�� db�� ����� ��й�ȣ�� ����
				closer = true;
				if(dao.updatePass(login,chgpass) > 0){
					msg="��й�ȣ�� ����Ǿ����ϴ�.";
				}else {
					msg="��й�ȣ ����� ������ �߻��߽��ϴ�.";
				}
			}else{
				msg="��й�ȣ �����Դϴ�. Ȯ���ϼ���.";
				url="passwordForm.do";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("opener", opener);
		request.setAttribute("closer", closer);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward write(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "���� �԰� ����";
		String url = "warehousing.do";
		
		Book book = new Book();
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublicationyear(Integer.parseInt(request.getParameter("publicationyear")));
		book.setPrice(Integer.parseInt(request.getParameter("price")));
		book.setIntroduction(request.getParameter("introduction"));
		BookDao dao = new BookDao();
		int num = dao.maxnum(); // board table���� num �÷��� �ִ밪 ����

		book.setNo(++num);
		if (dao.insert(book)) {
			msg = "���� �԰� ����";
			url = "blist.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {		}
		int limit = 10;// ���������� ����� �Խù� �Ǽ�
		BookDao dao = new BookDao(); 
		// ��ϵ� ��ü �Խù��� �Ǽ� �Ǵ�, �˻��� �Խù��� �Ǽ�
		int boardcount = dao.boardCount();
		// list: ȭ�鿡 ����� �Խñ� ���� ���
		List<Book> list = dao.list(pageNum, limit);
		int maxpage = (int) ((double) boardcount / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;// ������������ȣ
		int endpage = startpage + 9;// ���������� ��ȣ

		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcount - (pageNum - 1) * limit;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(new Date());

		request.setAttribute("boardcount", boardcount);
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("today", today);
		return new ActionForward();
	}
	
}
