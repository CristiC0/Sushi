package com.example.sushi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<SushiCard> sushiCards;
    private CheckBox checkbox;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter sushicardsAdapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static TextView totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCost=(TextView) findViewById(R.id.totalCost);
        checkbox=(CheckBox) findViewById(R.id.checkbox) ;

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                Toast.makeText(MainActivity.this, "Thanks for choosing us!", Toast.LENGTH_SHORT).show();
            }
        });

        DBConnection db=new DBConnection(this);
        sushiCards=db.getSushi();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        sushicardsAdapter=new SushiCardAdapter(MainActivity.this,sushiCards);
        recyclerView.setAdapter(sushicardsAdapter);

        calculateTotalCost();
    }

    public static void calculateTotalCost(){
        int totalSum=0;
        for (SushiCard sc:sushiCards) {
            totalSum+=sc.getCost()*sc.getQuantity();
        }
        totalCost.setText("Total Cost: "+totalSum+".0MDL");
    }
}