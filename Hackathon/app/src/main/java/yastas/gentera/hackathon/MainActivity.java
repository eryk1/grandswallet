package yastas.gentera.hackathon;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayoutRemesasMain;
    private Button btnCerrarSesionRemesa, btnDashboard;
    private Toolbar toolbarRemesasMain;
    private ActionBarDrawerToggle actionBarDrawerToggleRemesasMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniGuiMain();
        iniListenersGuiMain();
    }

    public void iniGuiMain(){
        drawerLayoutRemesasMain = (DrawerLayout) findViewById(R.id.drawer_layout_remesas);
        toolbarRemesasMain = (Toolbar) findViewById(R.id.appbarRemesasMain);
        btnDashboard = (Button) findViewById(R.id.btn_dashboard);
        btnCerrarSesionRemesa = (Button) findViewById(R.id.btn_cerrar_sesion_remesas);

        actionBarDrawerToggleRemesasMain = new ActionBarDrawerToggle(this,drawerLayoutRemesasMain, toolbarRemesasMain, R.string.app_name, R.string.app_name);
        drawerLayoutRemesasMain.addDrawerListener(actionBarDrawerToggleRemesasMain);
        actionBarDrawerToggleRemesasMain.syncState();

    }

    public void iniListenersGuiMain(){
        btnCerrarSesionRemesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
            }
        });

    }
}
