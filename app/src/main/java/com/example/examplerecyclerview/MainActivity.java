package com.example.examplerecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examplerecyclerview.entities.Adherent;
import com.example.examplerecyclerview.entities.Adherents;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = this;

        //On récupère notre recyclerview
        RecyclerView rcvAdherents = findViewById(R.id.rcvAdherents);
        //La liste des adhérents à afficher
        Adherents adherents = getAdherents();
        //On instancie notre adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(adherents);

        //permet de gérer la manière dont les élements(adhérents) vont être affichés à l'intérieur du recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        //Version GridView (avec spancount = le nombre d'éléments par colonne)
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(context, 3);

        rcvAdherents.setLayoutManager(layoutManager);

        //effets d'animation sur le recyclerview
        DefaultItemAnimator defaultItemAnimator= new DefaultItemAnimator();
        rcvAdherents.setItemAnimator(new DefaultItemAnimator());

        //On passe notre adapter à notre recyclerview
        rcvAdherents.setAdapter(recyclerViewAdapter);

    }

    private Adherents getAdherents()
    {
        Adherents adherents = new Adherents();

        Adherent adherent = null;
        for(int i = 1;i < 15;i++)
        {
            adherent = new Adherent("toto" + i, "titi" + i,i);
            adherents.add(adherent);
        }
        return adherents;
    }

    //l'adapter fait le lien entre le recycler view (qui attend de recevoir les éléments) et le viewholder(qui représente un élément)
    //l'adapter gère la liste des adherents pour notre recycler view qui ne fait qu'afficher
    public class RecyclerViewAdapter extends RecyclerView.Adapter<AdherentHolder>
    {
        //Notre liste d'adherents
        Adherents adherents;

        //On génère le constructeur
        public RecyclerViewAdapter(Adherents adherents) {

            //On initialise notre liste adherents via le constructeur
            this.adherents = adherents;
        }

        @NonNull
        @Override
        public AdherentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //On récupère le fichier xml graphique parmi les layouts
            //layoutInflate permet de convertir un layout en View
            //inflate = méthode permettant d'aller chercher un élément
            //View est la représentation objet d'un composant de type xml
            View view = LayoutInflater.from(context).inflate(R.layout.item_adherent,parent,false);

            //On passe notre objet(view) à notre viewHolder
            return new AdherentHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdherentHolder adherentHolder, int position) {

            //On récupère un adhérent parmi la liste adherents en fonction de sa position
            Adherent adherent = this.adherents.get(position);

            //On passe l'objet adherent à notre view holder
            adherentHolder.setAdherent(adherent);
        }


        //Combien on veut qu'il affiche d'éléments
        @Override
        public int getItemCount() {
            return adherents.size();
        }
    }


    public class AdherentHolder extends RecyclerView.ViewHolder
    {
        //On déclare les widgets
     public final TextView txtNom;

     public final TextView txtPrenom;

     public final TextView txtAge;

        public AdherentHolder(@NonNull View itemView) {
            super(itemView);

            //On initialise les widgets qui sont dans le layout
            txtNom = itemView.findViewById(R.id.txtNom);
            txtPrenom = itemView.findViewById(R.id.txtPrenom);
            txtAge = itemView.findViewById(R.id.txtAge);
        }

        public void setAdherent (Adherent adherent)
        {
            //Le match des données avec les widgets
            txtNom.setText(adherent.getNom());
            txtPrenom.setText(adherent.getPrenom());
            txtAge.setText("" + adherent.getAge());
        }
    }



}
