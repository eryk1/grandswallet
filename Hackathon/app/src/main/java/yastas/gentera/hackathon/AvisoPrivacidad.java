package yastas.gentera.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AvisoPrivacidad extends AppCompatActivity {
    private String privacyPolicies = "privacyPoliciesPHP.php";
    private TextView nameAppAviso, leyendaAvisoPriv;
    private Button btnCancelarAviso, btnAceptarAviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_privacidad);

        iniGUIAviso();
        iniListenersAviso();
    }

    public void iniGUIAviso(){
        nameAppAviso = (TextView)findViewById(R.id.tv_app_name_aviso);
        leyendaAvisoPriv = (TextView)findViewById(R.id.tv_contenedor_aviso);
        btnAceptarAviso = (Button)findViewById(R.id.btn_aceptar_aviso);
        btnCancelarAviso = (Button)findViewById(R.id.btn_cancelar_aviso);

    }

    public void iniListenersAviso(){
        btnAceptarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAceptar =  new Intent(AvisoPrivacidad.this, LoginPaso1.class);
                startActivity(intentAceptar);
            }
        });

        btnCancelarAviso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentCancel =  new Intent(AvisoPrivacidad.this, Login.class);
                startActivity(intentCancel);
                finish();
            }
        });

    }
}
