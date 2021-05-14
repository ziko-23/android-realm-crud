package com.example.crudrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Frog extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private int age;
    private String species;
    private String owner;
    public void set(Frog frog){
        this.name = frog.name;
        this.age = frog.age;
        this.species = frog.species;
        this.owner = frog.owner;
    }
    public Frog(String id,String name, int age, String species, String owner) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.owner = owner;
    }
    public Frog(String name, int age, String species, String owner) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.owner = owner;
    }

    public Frog() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    @Override
    public String toString() {
        return "Frog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
