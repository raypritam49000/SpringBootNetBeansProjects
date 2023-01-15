package com.telemune.mybatis.rest.api.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
      private int id;
      private String language;
      private List<Person> persons;
}
