package br.unipar.auxilioemergencial;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edCPF;
    private EditText edDataNasc;
    private EditText edRenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCPF = findViewById(R.id.edCPF);
        SimpleMaskFormatter simplemaskCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskcpf = new MaskTextWatcher(edCPF, simplemaskCPF);

        edDataNasc = findViewById(R.id.edDataNasc);
        SimpleMaskFormatter simplemaskData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskData = new MaskTextWatcher(edDataNasc, simplemaskData);


        edRenda = findViewById(R.id.edRenda);
        //SimpleMaskFormatter simplemaskRenda = new SimpleMaskFormatter("");
        //MaskTextWatcher maskRenda = new MaskTextWatcher(edRenda, simplemaskRenda);

        edCPF.addTextChangedListener(maskcpf);
        edDataNasc.addTextChangedListener(maskData);
        //edRenda.addTextChangedListener(maskRenda);

    }

    public void btnSolcitarOnClick(View view) {
        edDataNasc = findViewById(R.id.edDataNasc);
        edRenda = findViewById(R.id.edRenda);
        System.out.println(MainActivity.this.edDataNasc.getText().toString());
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putString("dataNasc", edDataNasc.getText().toString());
        b.putDouble("renda", Double.parseDouble(edRenda.getText().toString()));
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}