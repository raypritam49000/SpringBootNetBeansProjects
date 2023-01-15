package com.telemune.mybatis.rest.api.repository;

import com.telemune.mybatis.rest.api.models.Address;
import com.telemune.mybatis.rest.api.models.Language;
import com.telemune.mybatis.rest.api.models.Person;
import com.telemune.mybatis.rest.api.query.DBQuery;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface PersonRepository {

    @Select(DBQuery.GET_PERSON_BY_ID)
    @Results({
        @Result(id=true, property = "id", column = "id"),
        @Result(property="firstName", column="firstName"),
        @Result(property = "lastName", column = "lastName"),
        @Result(property="email", column="email"), 
        @Result(property="address", column="add_id", 
        one=@One(select="com.telemune.mybatis.rest.api.repository.PersonRepository.findAddessById")),
      })
    public abstract Person findPersonById(@Param("id") Long id);
    
    @Select(DBQuery.GET_PERSON_BY_NAME)
    @Results({
        @Result(id=true, property = "id", column = "id"),
        @Result(property="firstName", column="firstName"),
        @Result(property = "lastName", column = "lastName"),
        @Result(property="email", column="email"), 
        @Result(property="address", column="add_id", 
        one=@One(select="com.telemune.mybatis.rest.api.repository.PersonRepository.findAddessById")),
      })
    public abstract Person findPersonByEmail(@Param("email") String email);
    
    @Select(DBQuery.GET_PERSONS)
    @Results({
        @Result(id=true, property = "id", column = "id"),
        @Result(property="firstName", column="firstName"),
        @Result(property = "lastName", column = "lastName"),
        @Result(property="email", column="email"), 
        @Result(property="address", column="add_id", javaType = Address.class,
        one=@One(select="com.telemune.mybatis.rest.api.repository.PersonRepository.findAddessById"))
      })
    public abstract List<Person> findAll();
    
     @Select(DBQuery.GET_ADDRESS_BY_ID)
     @Results({
        @Result(id=true, property = "id", column = "id"),
        @Result(property = "city", column = "city"),
        @Result(property = "state", column = "state"),
        @Result(property = "country", column = "country")
      })
    public abstract Address findAddessById(@Param("id") Long id);
    
    
    @Select(DBQuery.FIND_LANGUAGE_BY_PERSON)
    @Results(value = {
        @Result(property="id", column="id"),
        @Result(property="language", column="language"),
        @Result(property="persons", javaType=List.class, column="person_id",
        many=@Many(select="com.telemune.mybatis.rest.api.repository.PersonRepository.findPersonById"))
    })
    public abstract List<Language> findLanuageByPerson(@Param("person_id") Long person_id);
    
    @Insert(DBQuery.SAVE_PERSON)
    public int save(Person person);
    
    
    @Delete(DBQuery.DELETE_PERSON_BY_ID)
    public int deletePersonById(long id);
    
    @Update(DBQuery.UPDATE_PERSON_BY_ID)
    public int updatePerson(Person person);
     
}
