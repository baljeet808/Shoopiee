package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import adapters.AlertAdapter;
import adapters.ImageLoader;
import adapters.ItemArrayClass;

public class ItemDetail extends AppCompatActivity {


    MainApplication mainApplication;
    ImageView itemImage,backButton;
    TextView price,itemName,addToBagButton,colorr,sizes;
    ItemArrayClass selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        selectedItem = mainApplication.getChoosenItem();

        price = (TextView) findViewById(R.id.price_textview);
        price.setText("NZD $"+selectedItem.price);
        colorr = (TextView) findViewById(R.id.color_textview);
        colorr.setText(selectedItem.colors.toUpperCase());
        sizes = (TextView) findViewById(R.id.size_textview);
        sizes.setText(selectedItem.sizes.toUpperCase());
        itemImage = (ImageView) findViewById(R.id.item_image_detail);
        ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(itemImage,selectedItem.imageName+".png");
        backButton = (ImageView) findViewById(R.id.back_button_detail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ItemDetail.this,Items.class);
                startActivity(i);
                finish();
            }
        });

        itemName = (TextView) findViewById(R.id.item_name_detail);
        itemName.setText(selectedItem.itemName);

        addToBagButton = (TextView) findViewById(R.id.add_Button_textview);
        addToBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertAdapter.getObject(ItemDetail.this).createAlert();
                Intent i = new Intent(ItemDetail.this,Cart.class);
                startActivity(i);
                finish();
            }
        });


    }
}
