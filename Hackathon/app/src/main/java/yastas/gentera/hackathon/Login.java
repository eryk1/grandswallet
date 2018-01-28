package yastas.gentera.hackathon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private EditText mailLogin, contrasennaLogin;
    private TextView nameAppLogin, avisoContrasennaLogin;
    private Button olvidoPass, nuevoRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        iniGUILogin();
        listenersLogin();
    }

    public void iniGUILogin(){
        nameAppLogin = (TextView)findViewById(R.id.tv_app_name_login);
        mailLogin = (EditText)findViewById(R.id.et_mail_login);
        contrasennaLogin = (EditText)findViewById(R.id.et_contrasenna_login);
        avisoContrasennaLogin = (TextView)findViewById(R.id.et_aviso_contrasenna_login);
        olvidoPass = (Button)findViewById(R.id.btn_olvidaste_tu_pass);
        nuevoRegistrate = (Button)findViewById(R.id.btn_nuevo_registrate);
    }

    public void listenersLogin(){
        contrasennaLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    avisoContrasennaLogin.setVisibility(View.VISIBLE);
                } else {
                    avisoContrasennaLogin.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Para testear la pagina tres sin registros
        olvidoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nuevoRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoRegistroIntent = new Intent(Login.this, AvisoPrivacidad.class);
                startActivity(nuevoRegistroIntent);
            }
        });

    }
}
