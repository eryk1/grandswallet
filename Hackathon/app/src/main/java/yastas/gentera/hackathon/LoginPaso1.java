package yastas.gentera.hackathon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPaso1 extends AppCompatActivity {
    TextView nameAppPasos;
    Button btnFlujoConsultante, btnFlujoConsultor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login_paso1);

        iniGUIPaso1();
        iniListenersPaso1();
    }

    public void iniGUIPaso1(){
        btnFlujoConsultante = (Button)findViewById(R.id.btn_flujo_consultante);
        btnFlujoConsultor = (Button)findViewById(R.id.btn_flujo_consultor);

    }

    public void iniListenersPaso1(){
        btnFlujoConsultante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFlujoConsultante = new Intent(LoginPaso1.this, LoginPaso2.class);
                startActivity(intentFlujoConsultante);
            }
        });

        btnFlujoConsultor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentFlujoConsultor = new Intent(LoginPaso1.this, );
                //startActivity(intentFlujoConsultor);
            }
        });

    }
}
