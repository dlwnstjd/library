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
			request.setAttribute("msg", "로그인 후 거래하세요");
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
		String msg = "아이디를 확인하세요";
		String url = "main.do";
		//1. 파라미터 저장
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//2. db 정보 읽기.
		Member mem = new MemberDao().selectOne(id);
		//3. 아이디, 비밀번호 검증
		if(mem != null){
			if(pass.equals(mem.getPass())){
				request.getSession().setAttribute("login", mem);
				request.getSession().setAttribute("login2", id);
				msg = mem.getName() + "님이 로그인 했습니다.";
				url = "main.do";
			}else{
				msg = "비밀번호가 틀립니다.";
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
		String msg = "회원가입 실패";  
		String url = "joinForm.do"; 
		MemberDao dao = new MemberDao();	//model 클래스
		int result = dao.insert(mem);
		if(result > 0){
			msg = mem.getName() + "님 회원 가입 완료";
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
				f.mkdirs();	//폴더가없을시 폴더 생성
			}
			MultipartRequest multi = new MultipartRequest
					(request, path, 10*1024*1024, "euc-kr");
			//fname: 업로드된 파일 이름
			fname = multi.getFilesystemName("picture");
			//썸네일 이미지 생성
			//new File(path + fname): 업로드된 원본 파일
			//bi: 메모리에 로드 정보
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

		String msg = "비밀번호가 틀렸습니다.";
		String url = "updateForm.do?id=" + mem.getId();
		MemberDao dao = new MemberDao();
		Member dbmem = dao.selectOne(mem.getId());
		String login = (String)request.getSession().getAttribute("login2");
		
		if(login.equals("admin") || mem.getPass().equals(dbmem.getPass())){
			int result = dao.update(mem);
			if(result > 0){
				return new ActionForward(true, "info.do?id=" + mem.getId());
			}else{
				msg = "수정실패";
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
			msg = "관리자는 탈퇴할 수 없습니다.";
			url = "info.do";
		}else {
			MemberDao dao = new MemberDao();
			Member dbmem = dao.selectOne(id);
			
			if(login.equals("admin") || pass.equals(dbmem.getPass())){
				if(dao.delete(id) > 0){	//삭제성공
					if(login.equals("admin")){	//관리자인경우
						msg = id + "사용자를 강제 탈퇴 성공";
						url = "mlist.do"; 
					} else{	//일반사용자인 경우
						msg = id + "님의 회원 탈퇴가 완료 되었습니다.";
						url = "main.do";
						request.getSession().invalidate();	//로그아웃
					}
				}else { //삭제 실패
					msg = id + "님의 탈퇴시 오류 발생";
					if(login.equals("admin")){	//관리자인 경우
						url = "info.do";
					}else{	//일반 사용자인경우
						url = "info.do?id=" + id;
					}
				}
			}else {	//일반사용자가 비밀번호가 틀린경우
				msg = id + "님의 비밀번호가 틀립니다.";
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
			msg = "로그인 하세요.";
			url = "main.do";
		}else{	//로그인상태
			String pass = request.getParameter("pass");	//변경전 비밀번호
			String chgpass = request.getParameter("chgpass");//변경후 비밀번호
			MemberDao dao = new MemberDao();
			Member mem = dao.selectOne(id);
			//mem.getPass(): db에 저장된 비밀번호
			if(pass.equals(mem.getPass())){	//입력된 비밀번호와 db에 저장된 비밀번호가 같은
				closer = true;
				if(dao.updatePass(login,chgpass) > 0){
					msg="비밀번호가 변경되었습니다.";
				}else {
					msg="비밀번호 변경시 오류가 발생했습니다.";
				}
			}else{
				msg="비밀번호 오류입니다. 확인하세요.";
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
		String msg = "도서 입고 실패";
		String url = "warehousing.do";
		
		Book book = new Book();
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublicationyear(Integer.parseInt(request.getParameter("publicationyear")));
		book.setPrice(Integer.parseInt(request.getParameter("price")));
		book.setIntroduction(request.getParameter("introduction"));
		BookDao dao = new BookDao();
		int num = dao.maxnum(); // board table에서 num 컬럼의 최대값 리턴

		book.setNo(++num);
		if (dao.insert(book)) {
			msg = "도서 입고 성공";
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
		int limit = 10;// 한페이지에 출력할 게시물 건수
		BookDao dao = new BookDao(); 
		// 등록된 전체 게시물의 건수 또는, 검색된 게시물의 건수
		int boardcount = dao.boardCount();
		// list: 화면에 출력할 게시글 내용 목록
		List<Book> list = dao.list(pageNum, limit);
		int maxpage = (int) ((double) boardcount / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;// 시작페이지번호
		int endpage = startpage + 9;// 종료페이지 번호

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
