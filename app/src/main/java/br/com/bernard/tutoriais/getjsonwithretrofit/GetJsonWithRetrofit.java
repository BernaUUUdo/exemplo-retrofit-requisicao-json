package br.com.bernard.tutoriais.getjsonwithretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.bernard.tutoriais.getjsonwithretrofit.clientapiinterface.GitHubUserApi;
import br.com.bernard.tutoriais.getjsonwithretrofit.model.GitHubUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



// 1. add permissoes no manifest
// 2. Adicionar retrofit 2 e GSon no gradle
// 3. Preparar gatilho para a requisição
// 4. Criar objeto para receber a resposta com o retrofit  ( GitHubUser)
// 5. Criar uma interface para a API GitHubUserApi
// 6. Instanciar o convertor Json           Gson gson = new GsonBuilder()
// 7. Instanciar o retrofit         Retrofit retrofit = new Retrofit.Builder()
// 8. Criar um objeto da API, a partir da interface
// 9. Criar uma chamada
// 10. Adicionar a fila de requisição
// 11. Preparar a implementação do Callback Callback<GitHubUser>
// 11. Implementar Callback de sucesso public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
// 13. Implementar Callback de falha     public void onFailure(Call<GitHubUser> call, Throwable t) {


public class GetJsonWithRetrofit extends AppCompatActivity implements Callback<GitHubUser> {

    Button btnGetJSon;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_json_with_retrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtResult = (TextView) findViewById(R.id.txtResult);
        btnGetJSon = (Button) findViewById(R.id.btnGetJSon);
        btnGetJSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult.setText("Buscando com o retrofit .... ");
                requestSomeInformation();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_json_with_retrofit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void requestSomeInformation(){
        String url = "https://api.github.com/users/du2x/following";


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubUserApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        GitHubUserApi githubUserAPI = retrofit.create(GitHubUserApi.class);

        Call<GitHubUser> call = githubUserAPI.getUser("du2x");
        //asynchronous call
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
        int code = response.code();
        if (code == 200) {
            GitHubUser user = response.body();
            txtResult.setText(user.toString());
        }else{
            txtResult.setText(response.message());
        }

    }

    @Override
    public void onFailure(Call<GitHubUser> call, Throwable t) {
        txtResult.setText(t.getMessage());
    }
}
