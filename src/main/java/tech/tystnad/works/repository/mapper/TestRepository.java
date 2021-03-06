package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tech.tystnad.works.model.City;
import tech.tystnad.works.model.Pet;

import java.util.Date;
import java.util.List;

@Repository
public interface TestRepository {

    @Select("SELECT id, name, countryCode, district, population FROM world.city LIMIT #{size}")
    List<City> findAllCity(@Param("size") int size);

    @Select("SELECT  name, owner, species, sex, birth, death FROM test.pet LIMIT #{size}")
    List<Pet> findAllPet(@Param("size") int size);

    @Select("SELECT  name, owner, species, sex, birth, death FROM test.pet WHERE birth > #{birth}")
    List<Pet> findPetByBirth(@Param("birth") Date birth);

    List<City> findByDistrict(@Param("district") String district);
}
