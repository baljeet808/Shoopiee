package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gauravkumar.com.shoopie.R;

public class CustomlistAdapter extends ArrayAdapter<SavedItems> {

    private Context context;
    private List<SavedItems> savedItemList = new ArrayList<>();

    public CustomlistAdapter(Context context,ArrayList<SavedItems> list)
    {
        super(context,0,list);
        this.context = context;
        savedItemList=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem==null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.custom_cart_list_item,parent,false);
            SavedItems savedItem = savedItemList.get(position);

            ImageView imageView = (ImageView) listItem.findViewById(R.id.image_li);
            ImageLoader.getObject(context).LoadImageFromUrl(imageView,savedItem.getItemName());

            TextView itemName = (TextView) listItem.findViewById(R.id.item_name_li);
            itemName.setText(savedItem.getItemName());

            TextView itemPrice = (TextView) listItem.findViewById(R.id.item_price_li);
            itemPrice.setText(savedItem.getPrice());

            TextView itemColor = (TextView) listItem.findViewById(R.id.item_description_li);
            itemColor.setText(savedItem.getColor());

            ImageView deleteButton = (ImageView) listItem.findViewById(R.id.delete_li);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"working on delete",Toast.LENGTH_SHORT).show();
                }
            });

            TextView editButton = (TextView) listItem.findViewById(R.id.item_edit_li);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "working on edit button", Toast.LENGTH_SHORT).show();
                }
            });


        }
        return listItem;
    }


}