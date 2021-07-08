package com.example.home2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home2.R;
import com.example.home2.pojo.Users;
import com.google.firebase.database.core.UserWriteRecord;

import java.util.ArrayList;
import java.util.List;

/*************** ESTA CLASE NOS VA AYUDAR A RELACIONAR TODO LO GRAFICO DEL BUSCAR_SERVICIO.XML CON LOS DATOS QUE SE VANA MOSTRAR***************/

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> {
    ArrayList<Users> usuarioList;
    int resource;

    public AdapterUsuario(ArrayList<Users> usuarioList, int resource){
        this.usuarioList = usuarioList;
        this.resource = resource;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {

        Users users = usuarioList.get(index);
        holder.name.setText(users.getNombres());
        //holder.city.setText(users.getCiudad());
        holder.status.setText(users.getServicio());
        holder.number.setText(users.getNumero());

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }
    /*private LayoutInflater mInflater;
    private Context context;*/

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,city,status,number;
        View view;
        public ViewHolder(View view){
            super(view);
            this.view=view;
            this.name = (TextView) view.findViewById(R.id.nameTextView);
            this.city = (TextView) view.findViewById(R.id.cityTextView);
            this.status = (TextView) view.findViewById(R.id.statusTextView);
            this.number = (TextView) view.findViewById(R.id.numberTextView);
            this.iconImage = (ImageView) view.findViewById(R.id.iconImageView);

        }

    }

    /*public AdapterUsuario(List<Users> usuarioList){
        this.usuarioList=usuarioList;
    }
    @NonNull
    @Override
    public int getItemCount(){return usuarioList.size();}

    @Override
    public viewholderusuario onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_servicio,parent, false);
        viewholderusuario holder = new viewholderusuario(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderusuario holder, int position){
        Users us = usuarioList.get(position);
        holder.name.setText(us.getNombres());
        holder.city.setText(us.getCiudad());
        holder.number.setText(us.getNumero());
        holder.status.setText(us.getServicio());

    }


    public class viewholderusuario extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,city,status,number;
        public viewholderusuario(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            status = itemView.findViewById(R.id.statusTextView);
            number = itemView.findViewById(R.id.numberTextView);

        }

        /*void bindData(final Users item){
           /* iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getNombres());
            city.setText(item.getCiudad());
            status.setText(item.getServicio());
            number.setText(item.getNumero());

        }
    }*/
}
