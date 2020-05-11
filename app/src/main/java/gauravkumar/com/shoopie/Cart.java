package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.AlertAdapter;
import adapters.CustomlistAdapter;
import adapters.ItemArrayClass;
import adapters.ItemsAdapter;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class Cart extends AppCompatActivity {

    MainApplication mainApplication;
    TextView buyButton;
    ImageView backButton;
    ListView savedItemsList;
    CustomlistAdapter adapter;
    ArrayList<ItemArrayClass> sItemList;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mainApplication = (MainApplication) this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        AlertAdapter.getObject(Cart.this).createAlert();
        Bundle bundle = new Bundle();
        bundle.putString("UID",SharedPreps.getStaticObject(Cart.this).getUID());
        bundle.putString("itemID",mainApplication.getChoosenItem().itemID);
        WebApiAdapter.getObject(Cart.this).fireServerApi(7,bundle);



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

    }

    public void onResponseReceive(String response) {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(Cart.this).createMessageAlert(response);
        }
        else {
            ItemsAdapter.getObject(Cart.this).setSavedItems(response);
            sItemList = ItemsAdapter.getObject(getApplicationContext()).getSavedItems();
            adapter = new CustomlistAdapter(getApplicationContext(), sItemList);
            savedItemsList.setAdapter(adapter);
        }
    }

    public void onResponse(String response)
    {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(Cart.this).createMessageAlert(response);
        }
        else {
            AlertAdapter.getObject(Cart.this).createAlert();
            Bundle bundle = new Bundle();
            bundle.putString("UID", SharedPreps.getStaticObject(Cart.this).getUID());
            WebApiAdapter.getObject(Cart.this).fireServerApi(6, bundle);
        }
    }
}
