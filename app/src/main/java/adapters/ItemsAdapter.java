package adapters;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemsAdapter {

    static Context con;
    static ItemsAdapter itemsAdapter;
    ItemArrayClass items;
    SavedItemArrayclass savedItem;
    ArrayList<ItemArrayClass> list;
    boolean itemsHere = false;
    ArrayList<SavedItemArrayclass> savedList;

    public ItemsAdapter(Context context)
    {
        con = context;
        list = new ArrayList<>();
        savedList = new ArrayList<>();
    }

    public static synchronized ItemsAdapter getObject(Context context)
    {
        con = context;
        if(itemsAdapter==null)
        {
            itemsAdapter = new ItemsAdapter(context);
        }
        return itemsAdapter;
    }
    public void setItemHereTrue()
    {
        itemsHere = true;
    }
    public boolean isItemsPresent(){
        return itemsHere;
    }

    public void saveAllItems(String response)
    {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i =0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                items = new ItemArrayClass();
                items.itemID = jsonObject.getString("iid");
                items.itemName = jsonObject.getString("itemName");
                items.colors = jsonObject.getString("color");
                items.sizes = jsonObject.getString("size");
                items.price = jsonObject.getString("price");
                items.gender = jsonObject.getString("gender");
                items.category = jsonObject.getString("category");
                items.imageName = jsonObject.getString("image");
                list.add(items);
            }
          }
        catch (Exception e)
        {
               AlertAdapter.getObject(con).createMessageAlert(e.getMessage());
        }
    }

    public ArrayList<ItemArrayClass> getItems(String gender,String category)
    {
        ArrayList<ItemArrayClass> arrayList = new ArrayList<>();
        for(int i = 0;i<32;i++)
        {
            items = list.get(i);
            if(items.gender.equalsIgnoreCase(gender) && items.category.equalsIgnoreCase(category))
            {
                arrayList.add(items);
               }
        }
        return arrayList;
    }

    public void setSavedItems(String response)
    {
        try {
            savedList.clear();
            JSONArray jsonArray = new JSONArray(response);
            for(int i =0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                savedItem = new SavedItemArrayclass();
                savedItem.itemID = jsonObject.getString("iid");
                savedItem.sid = jsonObject.getString("sid");
                savedList.add(savedItem);
            }
        }
        catch (Exception e)
        {
               AlertAdapter.getObject(con).createMessageAlert(e.getMessage());
        }
    }

    public ArrayList<ItemArrayClass> getSavedItems()
    {
        ArrayList<ItemArrayClass> arrayList = new ArrayList<>();

        for(int k =0;k<savedList.size();k++) {
            for (int i = 0; i < 32; i++) {
                if (list.get(i).itemID.equalsIgnoreCase(savedList.get(k).itemID)){
                    arrayList.add(list.get(i));
                }
            }
        }
        return arrayList;
    }

}


