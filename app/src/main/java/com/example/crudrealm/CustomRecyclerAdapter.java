package com.example.crudrealm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Frog> Frog;

    public CustomRecyclerAdapter(Context context, List Frog) {
        this.context = context;
        this.Frog = Frog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(Frog.get(position));

        Frog pu = Frog.get(position);

        holder.pName.setText(pu.getName());
        holder.pJobProfile.setText(pu.getSpecies());

    }

    @Override
    public int getItemCount() {
        return Frog.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pName;
        public TextView pJobProfile;

        public ViewHolder(View itemView) {
            super(itemView);
            pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pJobProfile = (TextView) itemView.findViewById(R.id.pJobProfiletxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Frog cpu = (Frog) view.getTag();
                    Intent send = new Intent(context,SingleProduct.class);
                    send.putExtra("id",cpu.getId());
                    send.putExtra("name",cpu.getName());
                    send.putExtra("action","update-delete");
                    context.startActivity(send);
                    Toast.makeText(view.getContext(), cpu.getId() + " is " + cpu.getSpecies(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
