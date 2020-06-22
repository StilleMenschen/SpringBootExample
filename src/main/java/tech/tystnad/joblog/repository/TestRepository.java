package tech.tystnad.joblog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tech.tystnad.joblog.model.City;
import tech.tystnad.joblog.model.Pet;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TestRepository {

    @Select("SELECT ID, NAME, CountryCode, District, Population FROM city LIMIT #{size}")
    List<City> findAllCity(@Param("size") int size);

    @Select("SELECT  name, owner, species, sex, birth, death FROM pet LIMIT #{size}")
    List<Pet> findAllPet(@Param("size") int size);

    @Select("SELECT  name, owner, species, sex, birth, death FROM pet WHERE birth > #{birth}")
    List<Pet> findPetByBirth(@Param("birth") Date birth);
}
