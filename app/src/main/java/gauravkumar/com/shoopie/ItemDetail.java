package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.ImageLoader;

public class ItemDetail extends AppCompatActivity {


    MainApplication mainApplication;
    ImageView itemImage,backButton;
    TextView price,itemName,addToBagButton;
    String selectedItemName = "";
    Spinner sizeSpinner;
    ArrayList<String> sizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        selectedItemName = mainApplication.getChoosenItemName();

        price = (TextView) findViewById(R.id.price_textview);
        itemImage = (ImageView) findViewById(R.id.item_image_detail);
        ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(itemImage,selectedItemName);
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
        itemName.setText(selectedItemName);
        sizes = new ArrayList<>();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizeSpinner = (Spinner) findViewById(R.id.size_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ItemDetail.this,android.R.layout.simple_spinner_item,sizes);
        sizeSpinner.setAdapter(adapter);

        addToBagButton = (TextView) findViewById(R.id.add_Button_textview);
        addToBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle b = new Bundle();
                b.putString("itemName",selectedItemName);
                b.putString("itemPrice",price.getText().toString());
                b.putString("itemSize",sizeSpinner.getSelectedItem().toString());
                b.putString("itemColor","Black");
                mainApplication.saveItemattributes(b);

                Intent i = new Intent(ItemDetail.this,Cart.class);
                startActivity(i);
                finish();
            }
        });


    }
}
