package com.example.antoine.quizanimaux;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        LinearLayout layout = (LinearLayout) findViewById(R.id.idLLayout);
        Button b = (Button) findViewById(R.id.buttonManage);

        b.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout layout2 = (LinearLayout) findViewById(R.id.idLLayout2);

                ViewGroup.LayoutParams params = layout2.getLayoutParams();
                params.height = 400;
                params.width = 400;
                layout2.setLayoutParams(params);
                for (int i = 0; i < 3; i++) {
                    LinearLayout row = new LinearLayout(layout2.getContext());
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    for (int j = 0; j < 4; j++) {
                        Button btnTag = new Button(layout2.getContext());
                        btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        btnTag.setText("Button " + (j + 1 + (i * 4)));
                        btnTag.setId(j + 1 + (i * 4));
                        row.addView(btnTag);
                    }

                    layout2.addView(row);
                }
            }
        });


        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List exempleList = new ArrayList();
        exempleList.add("Assinie");
        exempleList.add("Bassam");
        exempleList.add("Abidjan");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);

    }
}

