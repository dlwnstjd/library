package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Member;

public interface MemberMapper {

	@Insert("insert into member "
			+ "(id,pass,name,gender,email,picture) "
			+ "values(#{id},#{pass},#{name},#{gender},#{email},#{picture})")
	int insert(Member mem);

	@Select({"<script>",
		"select * from member",
		"<if test='id != null' > where binary id=#{id}</if>",
		"</script>"})
	List<Member> select(Map<String, Object> map);

	@Update("update member set name=#{name}, gender=#{gender}, "
			+ "email=#{email}, picture=#{picture} where id=#{id}")
	int update(Member mem);

	@Delete("delete from member where id=#{id}")
	int delete(@Param("id")String id);

	@Update("update member set pass=#{pass} where id=#{id}")
	int updatePass(@Param("id")String id,@Param("pass")String pass);
}
