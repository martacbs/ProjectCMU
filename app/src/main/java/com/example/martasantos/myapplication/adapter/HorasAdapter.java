package com.example.martasantos.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.martasantos.myapplication.evento.CriarEvento;
import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.models.Evento;
import com.example.martasantos.myapplication.models.Horas;

import java.util.List;

/**
 * Created by martasantos on 31/12/17.
 */

public class HorasAdapter extends RecyclerView.Adapter<HorasAdapter.ViewHolder> {

    private Context mContext;
    private List<Horas> mHoras;
    private List<Evento> evento;
    private Evento eventos;
   private  SharedPreferences preferences;
    private String eventoName;

    public HorasAdapter(Context mContext, List<Horas> mContacts, List<Evento> eventos) {
        this.mContext = mContext;
        this.mHoras = mContacts;
        this.evento = eventos;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Horas> getmContacts() {
        return mHoras;
    }

    public void setmContacts(List<Horas> mContacts) {
        this.mHoras = mContacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_layout,parent,false);

        ViewHolder viewHolder =new ViewHolder(contactView);



        preferences = PreferenceManager.getDefaultSharedPreferences(getmContext());
        eventoName = preferences.getString("nomeEvento", "");

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(HorasAdapter.ViewHolder viewHolder, int position){
        final Horas hora = mHoras.get(position);


        TextView textView1 = viewHolder.nameEvent;


        for(int i=0; i<mHoras.size(); i++) {
            if (i == position) {
                for (int j = 0; j < evento.size(); j++) {
                    if(j==position)
                    eventos = evento.get(position);
                    if (mHoras.get(i).getHora().equals(eventos.getComeca())) {
                        textView1.setText(eventoName.toString());
                    }
                }
            }
        }





        TextView textView=viewHolder.nameTextView;
        textView.setText(hora.getHora());


        Button button =viewHolder.messageButton;
        button.setText( "+" );


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               // int position=
               // Horas contact=mHoras.get(position);

                Intent myIntent = new Intent(mContext, CriarEvento.class);
                mContext.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHoras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView, nameEvent, comeca;
        public Button messageButton;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView =(TextView) itemView.findViewById(R.id.horas);
            messageButton= (Button) itemView.findViewById(R.id.message_button);
            nameEvent=(TextView)itemView.findViewById(R.id.eventos);
            comeca=(TextView) itemView.findViewById(R.id.comeca);


        }
    }

}

