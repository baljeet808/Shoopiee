package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {

    MainApplication mainApplication;
    TextView nameTV,address,country,subtotal,total,placeOrderButton,orderNumber,continueButton;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        nameTV = (TextView) findViewById(R.id.name_checkout);
        address = (TextView) findViewById(R.id.address_checkout);
        country = (TextView) findViewById(R.id.country_checkout);
        subtotal = (TextView) findViewById(R.id.subtotal_price);
        total = (TextView) findViewById(R.id.total_price);
        placeOrderButton = (TextView) findViewById(R.id.order_Button_textview);
        orderNumber = (TextView) findViewById(R.id.order_number);
        continueButton = (TextView) findViewById(R.id.continue_Button_textview);

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
}
