package tech.tystnad.joblog.test;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestRepository {

    @Select("SELECT ID, NAME, CountryCode, District, Population FROM city LIMIT #{size}")
    List<City> findAll(@Param("size") int size);
}
