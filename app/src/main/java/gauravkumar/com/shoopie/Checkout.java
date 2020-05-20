package gauravkumar.com.shoopie;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import adapters.AlertAdapter;
import adapters.ItemsAdapter;
import adapters.SavedItemArrayclass;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class Checkout extends AppCompatActivity {

    MainApplication mainApplication;
    TextView nameTV,address,country,subtotal,total,placeOrderButton,orderNumber,continueButton;
    ImageView backButton;
    String orderNum="";
    ProgressBar orderPlaceBar;
    LinearLayout finalLayout,detailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);


        finalLayout = (LinearLayout) findViewById(R.id.final_layout);
        detailLayout = (LinearLayout) findViewById(R.id.checkout_detail_layout);

        Random ran = new Random();
        orderNum = ""+SharedPreps.getStaticObject(Checkout.this).getUID()+""+(10000+ran.nextInt(999));

        nameTV = (TextView) findViewById(R.id.name_checkout);
        nameTV.setText(SharedPreps.getStaticObject(Checkout.this).getUserFName()+" "+SharedPreps.getStaticObject(Checkout.this).getUserLName());
        address = (TextView) findViewById(R.id.address_checkout);
        address.setText(SharedPreps.getStaticObject(Checkout.this).getAddress());
        country = (TextView) findViewById(R.id.country_checkout);
        country.setText(SharedPreps.getStaticObject(Checkout.this).getCountry());

        String sum = ItemsAdapter.getObject(Checkout.this).getCartTotalPrice();
        subtotal = (TextView) findViewById(R.id.subtotal_price);
        subtotal.setText("NZD $"+sum);
        total = (TextView) findViewById(R.id.total_price);
        total.setText("NZD $"+sum);

        orderPlaceBar = (ProgressBar) findViewById(R.id.order_Button_progressBar);

        orderNumber = (TextView) findViewById(R.id.order_number);
        orderNumber.setText(orderNum);

        Toast.makeText(getApplicationContext(),""+orderNum,Toast.LENGTH_SHORT).show();

        placeOrderButton = (TextView) findViewById(R.id.order_Button_textview);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrderButton.setVisibility(View.GONE);
                orderPlaceBar.setVisibility(View.VISIBLE);
                backButton.setEnabled(false);
                Bundle bundle = new Bundle();
                bundle.putString("UID",SharedPreps.getStaticObject(Checkout.this).getUID());
                bundle.putString("orderNumber",orderNum);

                WebApiAdapter.getObject(Checkout.this).fireServerApi(9,bundle);
            }
        });

        continueButton = (TextView) findViewById(R.id.continue_Button_textview);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Checkout.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });

        backButton = (ImageView)findViewById(R.id.back_button_checkout);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Checkout.this,Cart.class);
                startActivity(i);
                finish();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Checkout.this,Collection.class);
                startActivity(i);
                finish();
            }
        });
    }


    public void OnResponseByOrderTable(String response)
    {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(Checkout.this).createMessageAlert(response);
            placeOrderButton.setEnabled(false);
            placeOrderButton.setVisibility(View.VISIBLE);
            orderPlaceBar.setVisibility(View.GONE);
            backButton.setEnabled(true);
            placeOrderButton.setText("RETRY");
        }
        else{
            AlertAdapter.getObject(Checkout.this).createMessageAlert("order added to table");
            finalLayout.setVisibility(View.VISIBLE);
            detailLayout.setVisibility(View.GONE);
            Bundle bundle = new Bundle();
            bundle.putString("orderNumber",orderNum);
            ArrayList<SavedItemArrayclass> savedItems = ItemsAdapter.getObject(Checkout.this).getSavedItemsList();
            for(int i = 0;i<savedItems.size();i++) {
                bundle.putString("sid", savedItems.get(i).sid);
                bundle.putString("iid", savedItems.get(i).itemID);
                WebApiAdapter.getObject(Checkout.this).fireServerApi(10, bundle);
            }
              }
    }


}
