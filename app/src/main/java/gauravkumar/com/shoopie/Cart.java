package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.CustomlistAdapter;
import adapters.SavedItems;
import adapters.SharedPreps;

public class Cart extends AppCompatActivity {

    MainApplication mainApplication;
    TextView buyButton;
    ImageView backButton;
    ListView savedItemsList;
    CustomlistAdapter adapter;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mainApplication = (MainApplication) this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        ArrayList<SavedItems> itemList = new ArrayList<>();

        itemCount = SharedPreps.getStaticObject(getApplicationContext()).getSavedItemCount();
        itemCount++;
        SharedPreps.getStaticObject(getApplicationContext()).setSavedItemCount(itemCount);

        Bundle b = mainApplication.getSaveAttributes();
        SharedPreps.getStaticObject(getApplicationContext()).saveItem(itemCount,b.getString("itemName","abc"),b.getString("itemprice","$100"),b.getString("itemsize","S"),b.getString("itemColor","black"));

        for(int i=1;i<=itemCount;i++)
        {
            Bundle bun = SharedPreps.getStaticObject(getApplicationContext()).getSavedItem(i);
            itemList.add(new SavedItems(bun.getString("itemName","abc"),bun.getString("itemPrice","$100"),bun.getString("itemSize","S"),bun.getString("itemColor","black")));
        }

        adapter = new CustomlistAdapter(getApplicationContext(),itemList);


        buyButton = (TextView) findViewById(R.id.buy_Button_textview);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cart.this,Checkout.class);
                startActivity(i);
                finish();
            }
        });
        backButton = (ImageView) findViewById(R.id.back_button_cart);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cart.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });
        savedItemsList = (ListView) findViewById(R.id.saved_items_list);
        savedItemsList.setAdapter(adapter);

    }
}
