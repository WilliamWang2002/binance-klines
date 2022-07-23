package com.example.demo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ValidationMapper {
    @Select("SELECT COUNT(*) FROM kline WHERE run_name = #{runName}")
    public Integer count(String runName);
}


//@Mapper
//public interface EmployeeMyBatisRepository {
//    @Select("select * from employees")
//    public List < Employee > findAll();
//
//    @Select("SELECT * FROM employees WHERE id = #{id}")
//    public Employee findById(long id);
//
//    @Delete("DELETE FROM employees WHERE id = #{id}")
//    public int deleteById(long id);
//
//    @Insert("INSERT INTO employees(id, first_name, last_name,email_address) " +
//            " VALUES (#{id}, #{firstName}, #{lastName}, #{emailId})")
//    public int insert(Employee employee);
//
//    @Update("Update employees set first_name=#{firstName}, " +
//            " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
//    public int update(Employee employee);
//}
