package com.example.ZooFileHandlin;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Zoo {

    @Id
    private int animal_id;
    private String animal_name;
    private int totalNoOfAnimals;
    private String typeofAnimal;
}
