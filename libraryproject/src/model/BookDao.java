package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.BookMapper;

public class BookDao {
	private Class<BookMapper> cls = BookMapper.class;
	private Map<String,Object> map = new HashMap<>();
	public int maxnum() {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).maxnum();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}		
		return 0;
	}
	public boolean insert(Book book) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			session.getMapper(cls).insert(book);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}		
		return false;	
	}
	public int boardCount() {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();			
			System.out.println(map);
			return session.getMapper(cls).boardCount(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}		
		return 0;		
	}
	public List<Book> list(int pageNum, int limit) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("start", (pageNum-1)*limit);
			map.put("limit", limit);				
			return session.getMapper(cls).select(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return null;		 	
	}
	
	
}
