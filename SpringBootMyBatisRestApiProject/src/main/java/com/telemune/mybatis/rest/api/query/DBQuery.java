package com.telemune.mybatis.rest.api.query;

public class DBQuery {
    public final static String GET_PERSON_BY_ID = "SELECT * FROM persons WHERE id= #{id}";
    public final static String GET_PERSONS = "SELECT * FROM persons";
    public final static String GET_ADDRESS_BY_ID = "SELECT * FROM address WHERE id= #{id}";
    public final static String  FIND_LANGUAGE_BY_PERSON = "SELECT * FROM languages WHERE person_id= #{person_id}";
    public final static String GET_PERSON_BY_NAME = "SELECT * FROM persons WHERE email= #{email}";
    public final static String SAVE_PERSON = "INSERT INTO persons(firstName,lastName,email,add_id) VALUES (#{firstName}, #{lastName}, #{email},#{address.id})";
    public final static String DELETE_PERSON_BY_ID = "DELETE FROM persons WHERE id = #{id}";
    public static final String UPDATE_PERSON_BY_ID = "Update persons set firstName=#{firstName},lastName=#{lastName},email=#{email} where id=#{id}";
}
