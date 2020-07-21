package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Book;

public interface BookMapper {

	@Select("select ifnull(max(no),0) from book")
	int maxnum();

	@Insert("insert into book "
				+ "(name,author,publisher,publicationyear,price,"
				+ "introduction,whDate,status) "
				+ "value(#{name},#{author},#{publisher},#{publicationyear},"
				+ "#{price},#{introduction},now(),1)")
	boolean insert(Book book);

	@Select("select count(*) from book ")
	int boardCount(Map<String, Object> map);

	@Select({"<script>",	
		"select * from book ",
		"<choose>",
		"<when test='no !=null'>where no = #{no}</when>",
		"<otherwise> order by no desc "
			+ "limit #{start}, #{limit}</otherwise>",
		"</choose>",
		"</script>"})
	List<Book> select(Map<String, Object> map);

}
