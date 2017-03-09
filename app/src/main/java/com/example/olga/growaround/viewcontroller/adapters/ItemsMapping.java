package com.example.olga.growaround.viewcontroller.adapters;

import com.example.olga.growaround.R;

import java.util.HashMap;

/**
 * Created by Shay Lavi on 09/03/2017.
 */

public class ItemsMapping {

    private HashMap<Integer, Integer> itemResource = new HashMap<>();

    public ItemsMapping(){
        itemResource.put(0, R.drawable.cherry_tomato);
        itemResource.put(1, R.drawable.hasa);
        itemResource.put(2, R.drawable.nana);
    }

    public int getItem(int itemIndex){
        return itemResource.get(itemIndex);
    }

}
