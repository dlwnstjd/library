package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.MemberMapper;

public class MemberDao {
	private Class<MemberMapper> cls = MemberMapper.class;
	private Map<String,Object> map = new HashMap<>();

	public int insert(Member mem) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).insert(mem);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}		
		return 0;	
	}
	
	public Member selectOne(String id) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("id", id);
			List<Member> list = session.getMapper(cls).select(map);
			return list.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return null;
	}

	public int update(Member mem) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).update(mem);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return 0;	
	}

	public int delete(String id) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return 0;	
	}

	public int updatePass(String id, String pass) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).updatePass(id, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return 0;	 
	}
	
	public List<Member> list(){
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).select(null);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			MybatisConnection.close(session);
		}
		return null;
	}
}
