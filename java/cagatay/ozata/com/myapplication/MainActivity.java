package hw2.ozata.com.myapplication;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // database
    DatabaseHelper dbHelper;

    // recylerview
    private RecyclerView recyclerSocial;
    LinearLayoutManager layoutManager;
    MyRecyclerViewAdapter adapter;
    private List<Product> recyclervalues;

    //
    EditText id;
    EditText name;
    EditText type;
    Button dialog_button_add;
    Button dialog_button_back;
    Button listback;
    EditText delid;
    Button delbutton;
    Button delback;
    EditText searchbox;
    Button searchbutton;

    // dialog list
    Dialog list;
    Dialog add;
    Dialog del;
    Dialog search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database baglanti kur
        dbHelper=new DatabaseHelper(this);
        dbHelper.open();

    }

    public void onClick(View view) {

        switch (view.getId()){
            case(R.id.dialog_button_add):
                addDialog(0);
                break;
            case (R.id.button_update):
                addDialog(1);
                break;
            case(R.id.button_delete):
                delDialog();
                break;
            case (R.id.button_list):
                listDialog(0,"");
                break;
            case (R.id.buttonsearch):
                searchDialog();
                break;
        }

    }

    public void listDialog(int secim, String key){

        list = new Dialog(this);
        list.setContentView(R.layout.list_dialog);
        listback=list.findViewById(R.id.listback);

        // recycler
        recyclerSocial = list.findViewById(R.id.recyclerSocial);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSocial.setLayoutManager(layoutManager);
        recyclerSocial.hasFixedSize();

        // veritabanından datayı ekle
        if (secim==0)
            recyclervalues=ProductDB.getAllContact(dbHelper);
        else
            recyclervalues=ProductDB.findContact(dbHelper, key);

        adapter = new MyRecyclerViewAdapter(this, (ArrayList<Product>) recyclervalues);
        recyclerSocial.setAdapter(adapter);

        listback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.dismiss();
            }
        });

        list.show();
    }

    public void addDialog(final int secim){

        add = new Dialog(this);
        add.setContentView(R.layout.add_dialog);

        id=add.findViewById(R.id.editText);
        name=add.findViewById(R.id.editText2);
        type=add.findViewById(R.id.editText3);
        dialog_button_add=add.findViewById(R.id.dialog_button_add);
        dialog_button_back=add.findViewById(R.id.dialog_button_back);

        dialog_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add.dismiss();
            }
        });

        dialog_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secim==0)
                    ProductDB.insertContact(dbHelper,Integer.parseInt(id.getText().toString()),name.getText().toString(),type.getText().toString());
                else
                    ProductDB.updateContact(dbHelper,Integer.parseInt(id.getText().toString()),name.getText().toString(),type.getText().toString());
            }
        });

        add.show();
    }

    public void delDialog(){

        del = new Dialog(this);
        del.setContentView(R.layout.deldialog);

        delid=del.findViewById(R.id.delid);
        delback=del.findViewById(R.id.delback);
        delbutton=del.findViewById(R.id.delbutton);

        delback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del.dismiss();
            }
        });

        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDB.deleteContact(dbHelper,Integer.parseInt(delid.getText().toString()));
            }
        });

        del.show();
    }

    public void searchDialog(){

        search=new Dialog(this);
        search.setContentView(R.layout.search_dialog);

        searchbox=search.findViewById(R.id.searchbox);
        searchbutton=search.findViewById(R.id.searchbutton);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.dismiss();
                listDialog(1, searchbox.getText().toString());
            }
        });

        search.show();

    }

}
