package com.dao;

import com.entities.Flower;

import javax.swing.*;
import java.util.ArrayList;

public interface FlowerDAO {

    public Flower flowerInfo(Spring flowerId);


    public ArrayList getFlowerCatalog();
}
