package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep, Cadastrar, Alterar, Excluir, Limpar;
    EditText txtCep, edtNumero;
    TextView lblCEP, lblLogradouro, lblComplemento, lblBairro, lblCidade, lblEstado;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        lblCEP = findViewById(R.id.lblCEP);
        lblLogradouro = findViewById(R.id.lblLogradouro);
        lblComplemento = findViewById(R.id.lblComplemento);
        lblBairro = findViewById(R.id.lblBairro);
        lblCidade = findViewById(R.id.lblCidade);
        spinner = findViewById(R.id.lblEstado);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);
        edtNumero = findViewById(R.id.edtNumero);

        Cadastrar = findViewById(R.id.idCadastrar);
        Alterar = findViewById(R.id.idAlterar);
        Excluir = findViewById(R.id.idExcluir);
        Limpar = findViewById(R.id.idLimpar);
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCEP.setText(retorno.getCep().toString());
                    lblLogradouro.setText(retorno.getLogradouro().toString());
                    lblBairro.setText(retorno.getBairro().toString());
                    lblCidade.setText(retorno.getLocalidade().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                edtNumero.requestFocus();

            }
        });
        //adapter do array
        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.estado, android.R.layout.simple_spinner_item);
        //especificar o layout a ser usado
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //aplicar o adaptador no spinner
        spinner.setAdapter(adaptador);

        Excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Exlcuir", Toast.LENGTH_SHORT).show();
            }
        });

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cadastrar", Toast.LENGTH_SHORT).show();
            }
        });

        Limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Limpado Com sucesso!! ", Toast.LENGTH_SHORT).show();

                lblCEP.setText(" ");
                txtCep.setText(" ");
                edtNumero.setText(" ");
                lblLogradouro.setText(" ");
                lblBairro.setText(" ");
                lblCidade.setText(" ");
                lblEstado.setText(" ");
                lblComplemento.setText(" ");
                txtCep.requestFocus();

            }
        });
        Alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Alterar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}