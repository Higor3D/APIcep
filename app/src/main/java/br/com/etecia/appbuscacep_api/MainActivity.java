package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep, Cadastrar, Alterar, Excluir, Limpar;
    EditText txtCep;
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
                    lblComplemento.setText(retorno.getComplemento().toString());
                    lblBairro.setText(retorno.getBairro().toString());
                    lblCidade.setText(retorno.getLocalidade().toString());
                    lblEstado.setText(retorno.getUf().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        //adapter do array
        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.estado, android.R.layout.simple_spinner_item);
        //especificar o layout a ser usado
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //aplicar o adaptador no spinner
        spinner.setAdapter(adaptador);
    }
}