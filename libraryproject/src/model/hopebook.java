package model;

import java.util.Date;

public class hopebook {
	private int hbNo;
	private String name;
	private String author;
	private String publisher;
	private Date publicationyear;
	private int price;
	private int memberNo;
	
	public int getHbNo() {
		return hbNo;
	}
	public void setHbNo(int hbNo) {
		this.hbNo = hbNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublicationyear() {
		return publicationyear;
	}
	public void setPublicationyear(Date publicationyear) {
		this.publicationyear = publicationyear;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
}
