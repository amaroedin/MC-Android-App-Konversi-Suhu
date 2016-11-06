package com.example.amar.umby_konversisuhu;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.amar.umby_konversisuhu.R.id.konversi_CF;

public class MainActivity extends AppCompatActivity {

    TextView label_input, label_hasil;
    EditText txt_input, txt_hasil;
    RadioGroup pilih;
    RadioButton checkedCF, checkedFC;
    Button btn_proses;

    final Double const_c = 1.8, const_f = 0.55555555555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label_input = (TextView) findViewById(R.id.label_input);
        label_hasil = (TextView) findViewById(R.id.label_hasil);
        txt_input = (EditText) findViewById(R.id.txt_input);
        txt_hasil = (EditText) findViewById(R.id.txt_hasil);

        txt_input.setText("0");
        txt_hasil.setText("0");

        pilih = (RadioGroup) findViewById(R.id.pilih);
        pilih.check(konversi_CF);
        pilih.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checked) {
                if(konversi_CF == checked) {
                    label_input.setText("Celsius");
                    label_hasil.setText("Fahrenheit");
                }else{
                    label_input.setText("Fahrenheit");
                    label_hasil.setText("Celsius");
                }
            }
        });

        btn_proses = (Button) findViewById(R.id.btn_proses);
        btn_proses.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                txt_input = (EditText) findViewById(R.id.txt_input);
                if(txt_input.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Input suhu tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                }else{
                    convert();
                }
            }
        });
    }

    public void convert(){
        Double txtInput, txtResult;
        checkedCF=(RadioButton)findViewById(R.id.konversi_CF);
        checkedFC=(RadioButton)findViewById(R.id.konversi_FC);

        txtInput = Double.parseDouble(txt_input.getText().toString());
        if(checkedCF.isChecked()) {
            txtResult = (txtInput * const_c) + 32;
        }else{
            txtResult = const_f *(txtInput - 32);
        }
        txt_hasil.setText(String.valueOf(txtResult));
        Resources res = getResources();
        String hasil = String.format(res.getString(R.string.txtHasil), txtResult);
        txt_hasil.setText(hasil);
    }
}
