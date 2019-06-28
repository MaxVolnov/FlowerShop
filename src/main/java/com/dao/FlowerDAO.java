package com.dao;

import com.entities.Flower;

import java.util.ArrayList;

public interface FlowerDAO {

    public Flower flowerInfo(String flowerId);


    public ArrayList getFlowerCatalog();
}
