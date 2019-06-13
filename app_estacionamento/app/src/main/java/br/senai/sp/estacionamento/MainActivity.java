package br.senai.sp.estacionamento;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawerLayout,toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawer(Gravity.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menu_entrada:{
                Toast.makeText(this,"Entrada Veiculo",Toast.LENGTH_LONG).show();
                Intent intentCadastro = new Intent(MainActivity.this, ListaMovimentacao.class);
                startActivity(intentCadastro);
                break;
            }

            case R.id.menu_saida:{
                Toast.makeText(this,"Saída Veiculo",Toast.LENGTH_LONG).show();
                Intent intentSaida = new Intent(MainActivity.this, ListaSaida.class);
                startActivity(intentSaida);
                break;
            }

            case R.id.cadastrar_mensalista:{
                Toast.makeText(this,"Cadastrar Mensalista",Toast.LENGTH_LONG).show();
                Intent intentCadastroMensalista = new Intent(MainActivity.this, CadastroMensalista.class);
                startActivity(intentCadastroMensalista);
                break;
            }

            case R.id.receber_mensalidade:{
                Toast.makeText(this,"Receber Mensalidade",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.cadastrar_preco:{
                Intent intentPreco= new Intent(MainActivity.this, CadastroPrecoActivity.class);
                startActivity(intentPreco);

                Toast.makeText(this,"Cadastrar Preco",Toast.LENGTH_LONG).show();
                break;
            }

        }

        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
