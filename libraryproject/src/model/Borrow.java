package model;

import java.util.Date;

public class Borrow {
	private int brNo;
	private Date brdate;
	private Date returndate;
	private int bookstate;
	private int no;
	private int memberNo;
	
	public int getBrNo() {
		return brNo;
	}
	public void setBrNo(int brNo) {
		this.brNo = brNo;
	}
	public Date getBrdate() {
		return brdate;
	}
	public void setBrdate(Date brdate) {
		this.brdate = brdate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public int getBookstate() {
		return bookstate;
	}
	public void setBookstate(int bookstate) {
		this.bookstate = bookstate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
}
