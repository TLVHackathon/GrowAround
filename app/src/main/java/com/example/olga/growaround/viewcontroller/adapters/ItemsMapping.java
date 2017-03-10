package com.example.olga.growaround.viewcontroller.adapters;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.itemsWrapper;

import java.util.HashMap;

/**
 * Created by Shay Lavi on 09/03/2017.
 */

public class ItemsMapping {


    private HashMap<Integer, itemsWrapper> itemResource = new HashMap<>();

    public ItemsMapping(){
        itemResource.put(0, new itemsWrapper(R.drawable.cherry_tomato, "עגבניית צ'רי"));
        itemResource.put(1, new itemsWrapper(R.drawable.hasa, "חסה בייב"));
        itemResource.put(2, new itemsWrapper(R.drawable.nana, "נענע בייב"));
    }

    public itemsWrapper getItemsWrapper(int itemIndex){
        return itemResource.get(itemIndex);
    }

}
