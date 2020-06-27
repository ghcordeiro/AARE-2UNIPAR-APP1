package br.unipar.auxilioemergencial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import br.unipar.auxilioemergencial.util.Analise;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle b = getIntent().getExtras();
        ArrayList<String> resultadoAnalise = new ArrayList<String >();
        try {
            resultadoAnalise = Analise.retornaResulado(Objects.requireNonNull(b.getString("dataNasc")), b.getDouble("renda"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView status = findViewById(R.id.tvStatus);
        TextView motivo = findViewById(R.id.tvMotivo);
        TextView valor = findViewById(R.id.tvValor);

        status.setText(resultadoAnalise.get(0));
        motivo.setText(resultadoAnalise.get(1));
        valor.setText(resultadoAnalise.get(2));
    }

}
