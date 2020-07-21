package model;

import java.util.Date;

public class Book {
	private int no;
	private String name;
	private String author;
	private String publisher;
	private int publicationyear;
	private int price;
	private String introduction;
	private Date whDate;
	private int status;
		
	public Date getWhDate() {
		return whDate;
	}
	public void setWhDate(Date whDate) {
		this.whDate = whDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getPublicationyear() {
		return publicationyear;
	}
	public int setPublicationyear(int publicationyear) {
		return this.publicationyear = publicationyear;
	}
	public int getPrice() { 
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
