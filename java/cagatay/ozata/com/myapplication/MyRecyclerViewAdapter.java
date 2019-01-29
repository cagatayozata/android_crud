package hw2.ozata.com.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Product> recyclerItemValues;

    public MyRecyclerViewAdapter(Context context, ArrayList<Product>values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.reccyler_layout, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Product sm = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tv.setText(sm.getId()+" - "+sm.getName()+" - "+sm.getType());

        if (sm.getId()==0)
            myRecyclerViewItemHolder.img.setImageResource(R.drawable.facebook);
        else if (sm.getId()==1)
            myRecyclerViewItemHolder.img.setImageResource(R.drawable.gmail);
        else
            myRecyclerViewItemHolder.img.setImageResource(R.drawable.twitter);

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked on item "+sm.getName(), Toast.LENGTH_LONG).show();
            }
        });

        myRecyclerViewItemHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked on "+ sm.getName()+" Image of item", Toast.LENGTH_LONG).show();
            }
        });

        myRecyclerViewItemHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked on "+ sm.getName()+" TextView of item", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

     class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.img);
            parentLayout = itemView.findViewById(R.id.constLayout);
        }
    }

}
