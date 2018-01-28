package yastas.gentera.hackathon;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class LoginPaso2 extends AppCompatActivity implements TextWatcher {
    private ImageView btnCamara, ponerAvatar, tvPonerFoto;
    private EditText etPrimerNombre, etSegundoNombre, etApellidosPaterno, etApellidosMaterno, etHomoClaveCurp, etMailPaso2, etCelularPaso2, etCodigoPostal, etEstadoResidencia, etMunicipio, etColonia, etCalle, etNumExterior, etNumInterior, etClaveElector, etNewPassPaso2, etConfirmPassPaso2;
    private Spinner spLugarNacimiento, spGeneroPaso2;
    private String[] itemsGenero, itemsEntidades, pocoConvenientes;
    private TextView tvFechaNacPaso2, tvCurp, tvControlFotoIne, ivFotoIne;
    private Button btnCancelar, btnEnviar;
    private char c1,c2, c3, c4, cg, c14, c15, c16;
    private String genero, siglasEntidad, armandoCurp, annoCortoTemporal, mesTemporal, diaTemporal, curpCompleto;
    private boolean primerVocal, boolc14, boolc15, boolc16;
    private static final int FOTO_INE = 002, FOTO_PERFIL = 001;
    private char consonantes[] = {'b','B','c','C','d','D','f','F','g','G','h','H','j','J','k','K','l','L','m','M','n','N','ñ','Ñ','p','P','q','Q','r','R','s','S','t','T','v','V','w','W','x','X','y','Y','z','Z'};
    private AlertDialog.Builder dialogPaso1;
    private Bitmap bitmapIne, bitmapFotoPerfil;
    private int infoGenero, anno, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_paso2);

        iniGUILogin2();
        iniListenersGUILogin2();
    }

    public void iniGUILogin2(){
        ponerAvatar = (ImageView)findViewById(R.id.iv_avatar_perfil);
        btnCamara = (ImageView)findViewById(R.id.btn_foto_perfil);
        tvFechaNacPaso2 = (TextView) findViewById(R.id.et_fecha_nacimiento_n2);
        etClaveElector = (EditText)findViewById(R.id.et_numero_de_identificacion_clave_de_elector_n2);
        etPrimerNombre = (EditText)findViewById(R.id.et_primer_nombre_nueva_n2);
        etSegundoNombre = (EditText)findViewById(R.id.et_segundo_nombre_nueva_n2);
        etApellidosPaterno = (EditText)findViewById(R.id.et_apellido_paterno_nueva_n2);
        etApellidosMaterno = (EditText)findViewById(R.id.et_apellido_materno_nueva_n2);
        tvCurp = (TextView) findViewById(R.id.et_curp_nueva_n2);
        etHomoClaveCurp = (EditText) findViewById(R.id.et_homoclave_curp_beneficiario_remesas);
        etCelularPaso2 = (EditText) findViewById(R.id.et_telefono_contacto_nueva_n2);
        etMailPaso2 = (EditText)findViewById(R.id.et_correo_electronico_nueva_n2);
        etCodigoPostal = (EditText) findViewById(R.id.et_codigo_postal_nueva_n2);
        etEstadoResidencia = (EditText) findViewById(R.id.et_estado_nueva_n2);
        etMunicipio = (EditText) findViewById(R.id.et_delegacion_nueva_n2);
        etColonia = (EditText) findViewById(R.id.et_colonia_nueva_n2);
        etCalle = (EditText) findViewById(R.id.et_calle_nueva_n2);
        etNumExterior = (EditText) findViewById(R.id.et_numero_exterior_nueva_n2);
        etNumInterior= (EditText) findViewById(R.id.et_numero_interior_nueva_n2);
        etClaveElector = (EditText) findViewById(R.id.et_numero_de_identificacion_clave_de_elector_n2);
        tvControlFotoIne = (TextView) findViewById(R.id.iv_foto_ine_perfil);
        ivFotoIne = (TextView) findViewById(R.id.iv_foto_ine_perfil);
        tvPonerFoto = (ImageView) findViewById(R.id.et_foto_ine_n2);
        etNewPassPaso2 = (EditText) findViewById(R.id.et_contrasenna_nueva_n2);
        etConfirmPassPaso2 = (EditText) findViewById(R.id.et_comfirmar_contrasenna_nueva_n2);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar_n2);
        btnEnviar = (Button) findViewById(R.id.btn_enviar_n2);

        tvFechaNacPaso2.addTextChangedListener(this);
        etPrimerNombre.addTextChangedListener(this);
        etSegundoNombre.addTextChangedListener(this);
        etApellidosPaterno.addTextChangedListener(this);
        etApellidosMaterno.addTextChangedListener(this);


        spLugarNacimiento = (Spinner)findViewById(R.id.sp_lugar_nacimiento);
        itemsEntidades = getResources().getStringArray(R.array.entidad_federativa);

        spGeneroPaso2 = (Spinner)findViewById(R.id.sp_genero);
        itemsGenero = getResources().getStringArray(R.array.genero);

        ArrayAdapter<String> adapadorSpinner = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, itemsGenero);
        adapadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGeneroPaso2.setAdapter(adapadorSpinner);

        ArrayAdapter<String> adapadorSpinnerEntidad = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, itemsEntidades);
        adapadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLugarNacimiento.setAdapter(adapadorSpinnerEntidad);


        //spGeneroPaso2.setPrompt(getResources().getString(R.string.genero));
/*        etMailPaso2 = (EditText)findViewById(R.id.et_mail_login_paso2);
        tvFechaNacPaso2 = (TextView)findViewById(R.id.tv_fecha_nacimiento_paso2);
        etCelularPaso2 = (EditText)findViewById(R.id.et_celular_paso2);
        etNewPassPaso2 = (EditText)findViewById(R.id.et_contrasenna_paso2);
        etConfirmPassPaso2 = (EditText)findViewById(R.id.et_confirmar_contrasenna_paso2);
        btnPaso2_3 = (Button)findViewById(R.id.btn_paso2_alpaso3);*/

    }

    public void iniListenersGUILogin2(){
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFotoPerfil = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentFotoPerfil.putExtra("android.intent.extras.CAMERA_FACING", 1);
                startActivityForResult(intentFotoPerfil, FOTO_PERFIL);
            }

        });

        tvFechaNacPaso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar fechaSistema = Calendar.getInstance();
                anno = fechaSistema.get(Calendar.YEAR);
                mes = fechaSistema.get(Calendar.MONTH);
                dia = fechaSistema.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LoginPaso2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int aaaa, int mm, int dd) {
                        if (mm < 10 && dd < 10) {
                            tvFechaNacPaso2.setText(aaaa + "/0" + (mm + 1) + "/0" + dd);
                        } else if (mm < 9) {
                            tvFechaNacPaso2.setText(aaaa + "/0" + (mm + 1) + "/" + dd);
                        } else if (dd < 10) {
                            tvFechaNacPaso2.setText(aaaa + "/" + (mm + 1) + "/0" + dd);
                        } else {
                            tvFechaNacPaso2.setText(aaaa + "/" + (mm + 1) + "/" + dd);
                        }
                    }
                }, anno, mes, dia);
                datePickerDialog.show();
            }
        });

        spGeneroPaso2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cg = ' ';
                if (position == 1) {
                    cg = 'H';
                    genero = itemsGenero[position];
                } else if (position == 2) {
                    cg = 'M';
                    genero = itemsGenero[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spLugarNacimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                siglasEntidad = "  ";
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        siglasEntidad = "AS";
                        break;
                    case 2:
                        siglasEntidad = "BC";
                        break;
                    case 3:
                        siglasEntidad = "BS";
                        break;
                    case 4:
                        siglasEntidad = "CC";
                        break;
                    case 5:
                        siglasEntidad = "DF";
                        break;
                    case 6:
                        siglasEntidad = "CL";
                        break;
                    case 7:
                        siglasEntidad = "CM";
                        break;
                    case 8:
                        siglasEntidad = "CS";
                        break;
                    case 9:
                        siglasEntidad = "CH";
                        break;
                    case 10:
                        siglasEntidad = "DG";
                        break;
                    case 11:
                        siglasEntidad = "GT";
                        break;
                    case 12:
                        siglasEntidad = "GR";
                        break;
                    case 13:
                        siglasEntidad = "HG";
                        break;
                    case 14:
                        siglasEntidad = "JC";
                        break;
                    case 15:
                        siglasEntidad = "MC";
                        break;
                    case 16:
                        siglasEntidad = "MN";
                        break;
                    case 17:
                        siglasEntidad = "MS";
                        break;
                    case 18:
                        siglasEntidad = "NT";
                        break;
                    case 19:
                        siglasEntidad = "NL";
                        break;
                    case 20:
                        siglasEntidad = "OC";
                        break;
                    case 21:
                        siglasEntidad = "PL";
                        break;
                    case 22:
                        siglasEntidad = "QT";
                        break;
                    case 23:
                        siglasEntidad = "QR";
                        break;
                    case 24:
                        siglasEntidad = "SP";
                        break;
                    case 25:
                        siglasEntidad = "SL";
                        break;
                    case 26:
                        siglasEntidad = "SR";
                        break;
                    case 27:
                        siglasEntidad = "TC";
                        break;
                    case 28:
                        siglasEntidad = "TS";
                        break;
                    case 29:
                        siglasEntidad = "TL";
                        break;
                    case 30:
                        siglasEntidad = "VZ";
                        break;
                    case 31:
                        siglasEntidad = "YN";
                        break;
                    case 32:
                        siglasEntidad = "ZS";
                        break;
                    case 33:
                        siglasEntidad = "NE";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvControlFotoIne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIne = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIne, FOTO_INE);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPaso2.this, Login.class));
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curpCompleto = armandoCurp + etHomoClaveCurp.getText().toString();

                if(etPrimerNombre.getText().toString().equals("") ||
                        etApellidosPaterno.getText().toString().equals("") ||
                        etApellidosMaterno.getText().toString().equals("") ||
                        curpCompleto.length() < 18 ||
                        etCelularPaso2.getText().toString().equals("") ||
                        etMailPaso2.getText().toString().equals("") ||
                        tvFechaNacPaso2.getText().toString().equals("") ||
                        etCodigoPostal.getText().toString().equals("") ||
                        etMunicipio.getText().toString().equals("") ||
                        etColonia.getText().toString().equals("") ||
                        etCalle.getText().toString().equals("") ||
                        etNumInterior.getText().toString().equals("") ||
                        etClaveElector.getText().toString().equals("") ||
                        etNewPassPaso2.getText().toString().equals("") ||
                        etConfirmPassPaso2.getText().toString().equals("") /*||*/
                        ) {
                    dialogPaso1 = new AlertDialog.Builder(LoginPaso2.this);
                    dialogPaso1.setTitle("Algo salio mal");
                    dialogPaso1.setMessage("Por favor introduce todos los datos");
                    dialogPaso1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialogPaso1.create();
                    dialogPaso1.show();
                }/* else if(etMailPaso2.){

                }*/
                else if(etCelularPaso2.length()<10){
                    dialogPaso1 = new AlertDialog.Builder(LoginPaso2.this);
                    dialogPaso1.setTitle("Algo salio mal");
                    dialogPaso1.setMessage("Introduce completo tu numero celular");
                    dialogPaso1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            etNewPassPaso2.setText("");
                            etConfirmPassPaso2.setText("");
                        }
                    });
                    dialogPaso1.create();
                    dialogPaso1.show();
                } else if(etNewPassPaso2.length()<8){
                    dialogPaso1 = new AlertDialog.Builder(LoginPaso2.this);
                    dialogPaso1.setTitle("Algo salio mal");
                    dialogPaso1.setMessage("La contraseña es muy corta");
                    dialogPaso1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            etNewPassPaso2.setText("");
                            etConfirmPassPaso2.setText("");
                        }
                    });
                    dialogPaso1.create();
                    dialogPaso1.show();

                }
                else if(!(etNewPassPaso2.getText().toString().equals(etConfirmPassPaso2.getText().toString()))
                        ) {
                    dialogPaso1 = new AlertDialog.Builder(LoginPaso2.this);
                    dialogPaso1.setTitle("Algo salio mal");
                    dialogPaso1.setMessage("Las contraseñas deben coincidir");
                    dialogPaso1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            etNewPassPaso2.setText("");
                            etConfirmPassPaso2.setText("");
                        }
                    });
                    dialogPaso1.create();
                    dialogPaso1.show();
                } else {
                    BackgroundTaskRegistro backgroundTaskRegistro = new BackgroundTaskRegistro(LoginPaso2.this);
                    backgroundTaskRegistro.execute("registro_comercio", etPrimerNombre.toString(), etSegundoNombre.getText().toString(),
                            etApellidosPaterno.getText().toString(), etApellidosMaterno.getText().toString(), tvFechaNacPaso2.getText().toString(),
                            curpCompleto, etCelularPaso2.getText().toString(), etMailPaso2.getText().toString(), etCodigoPostal.getText().toString(),
                            etEstadoResidencia.getText().toString(), etMunicipio.getText().toString(), etColonia.getText().toString(),
                            etCalle.getText().toString(), etNumExterior.getText().toString(), etNumInterior.getText().toString(),
                            bitmapFotoPerfil.toString(), bitmapIne.toString(), etClaveElector.getText().toString(), etNewPassPaso2.getText().toString());

                    Intent intentPaso2al3 = new Intent(LoginPaso2.this, MainActivity.class);
                    startActivity(intentPaso2al3);
                }

            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        armandoCurp ="";
        c1 = ' ';
        c2 = ' ';
        c3 = ' ';
        c4 = ' ';
        annoCortoTemporal = "  ";
        mesTemporal = "  ";
        diaTemporal = "  ";
        c14 = ' ';
        c15 = ' ';
        c16 = ' ';

    }

    @Override
    public void afterTextChanged(Editable s) {
        primerVocal = false;
        boolc14 = false;
        boolc15 = false;
        boolc16 = false;

        if(etApellidosPaterno.length()>0) {
            c1 = etApellidosPaterno.getText().charAt(0);
            if (c1==('a') || c1==('A') || c1==('á') || c1==('Á')) {
                c1 = 'A';
            } else if (c1==('e') || c1==('E') || c1==('é') || c1==('É')) {
                c1 = 'E';
            } else if (c1==('i') || c1==('I') || c1==('í') || c1==('Í')) {
                c1 = 'I';
            } else if (c1==('o') || c1==('O') || c1==('ó') || c1==('Ó')) {
                c1 = 'O';
            } else if (c1==('u') || c1==('U') || c1==('ú') || c1==('Ú') || c1==('ü') || c1==('Ü')) {
                c1 = 'U';
            } else if (c1==('ñ') || c1==('Ñ') || c1==('/') || c1==('-') || c1==('.')) {
                c1 = 'X';
            }

            for (int i = 1; i < etApellidosPaterno.length() && primerVocal == false; i++) {
                c2 = etApellidosPaterno.getText().charAt(i);
                if (c2 == 'a' || c2 == 'A' || c2 == 'á' || c2 == 'Á') {
                    c2 = 'A';
                    primerVocal = true;
                } else if (c2 == 'e' || c2 == 'E' || c2 == 'é' || c2 == 'É') {
                    c2 = 'E';
                    primerVocal = true;
                } else if (c2 == 'i' || c2 == 'I' || c2 == 'í' || c2 == 'Í') {
                    c2 = 'I';
                    primerVocal = true;
                } else if (c2 == 'o' || c2 == 'O' || c2 == 'ó' || c2 == 'Ó') {
                    c2 = 'O';
                    primerVocal = true;
                } else if (c2 == 'u' || c2 == 'U' || c2 == 'ú' || c2 == 'Ú' || c2 == 'ü' || c2 == 'Ü') {
                    c2 = 'U';
                    primerVocal = true;
                } else {
                    c2 = 'X';
                }
            }

            for (int i=1; i <etApellidosPaterno.length() && boolc14 == false; i++){
                for (int j=0; j < consonantes.length; j++ ){
                    c14 = etApellidosPaterno.getText().charAt(i);
                    if(c14 == consonantes[j]){
                        boolc14 = true;
                    }
                }
            }
            if (c14 == ('a') || c14 == ('A') || c14 == ('á') || c14 == ('Á')) {
                c14 = 'X';
            } else if (c14 == ('e') || c14 == ('E') || c14 == ('é') || c14 == ('É')) {
                c14 = 'X';
            } else if (c14 == ('i') || c14 == ('I') || c14 == ('í') || c14 == ('Í')) {
                c14 = 'X';
            } else if (c14 == ('o') || c14 == ('O') || c14 == ('ó') || c14 == ('Ó')) {
                c14 = 'X';
            } else if (c14 == ('u') || c14 == ('U') || c14 == ('ú') || c14 == ('Ú')) {
                c14 = 'X';
            } else if (c14 == ('ñ') || c14 == ('Ñ') || c14==('/') || c14==('-') || c14==('.') || c14==(' ')) {
                c14 = 'X';
            }
        }

        if(etApellidosMaterno.length()>0){
            c3 = etApellidosMaterno.getText().charAt(0);
            if (c3==('a') || c3==('A') || c3==('á') || c3==('Á')) {
                c3 = 'A';
            } else if (c3==('e') || c3==('E') || c3==('é') || c3==('É')) {
                c3 = 'E';
            } else if (c3==('i') || c3==('I') || c3==('í') || c3==('Í')) {
                c3 = 'I';
            } else if (c3==('o') || c3==('O') || c3==('ó') || c3==('Ó')) {
                c3 = 'O';
            } else if (c3==('u') || c3==('U') || c3==('ú') || c3==('Ú') || c3==('ü') || c3==('Ü')) {
                c3 = 'U';
            } else if (c3==('ñ') || c3==('Ñ') || c3==('/') || c3==('-') || c3==('.')) {
                c3 = 'X';
            }

            for (int i=1; i <etApellidosMaterno.length() && boolc15 == false; i++){
                for (int j=0; j < consonantes.length; j++ ){
                    c15 = etApellidosMaterno.getText().charAt(i);
                    if(c15 == consonantes[j]){
                        boolc15 = true;
                    }
                }
            }
            if (c15 == ('a') || c15 == ('A') || c15 == ('á') || c15 == ('Á')) {
                c15 = 'X';
            } else if (c15 == ('e') || c15 == ('E') || c15 == ('é') || c15 == ('É')) {
                c15 = 'X';
            } else if (c15 == ('i') || c15 == ('I') || c15 == ('í') || c15 == ('Í')) {
                c15 = 'X';
            } else if (c15 == ('o') || c15 == ('O') || c15 == ('ó') || c15 == ('Ó')) {
                c15 = 'X';
            } else if (c15 == ('u') || c15 == ('U') || c15 == ('ú') || c15 == ('Ú')) {
                c15 = 'X';
            } else if (c15 == ('ñ') || c15 == ('Ñ') || c15==('/') || c15==('-') || c15==('.') || c15==(' ')) {
                c15 = 'X';
            }
        } else {
            c3 = 'X';
            c15 = 'X';

        }

        if(etPrimerNombre.length()>0){
            if(etPrimerNombre.getText().toString().equals("Maria") || etPrimerNombre.getText().toString().equals("Ma,") ||
                    etPrimerNombre.getText().toString().equals("Ma.") || etPrimerNombre.getText().toString().equals("Ma") ||
                    etPrimerNombre.getText().toString().equals("Jose") || etPrimerNombre.getText().toString().equals("J.") ||
                    etPrimerNombre.getText().toString().equals("J,") || etPrimerNombre.getText().toString().equals("J")){
                if(etSegundoNombre.length()>0){
                    c4 = etSegundoNombre.getText().charAt(0);
                    if (c4 == ('a') || c4 == ('A') || c4 == ('á') || c4 == ('Á')) {
                        c4 = 'A';
                    } else if (c4 == ('e') || c4 == ('E') || c4 == ('é') || c4 == ('É')) {
                        c4 = 'E';
                    } else if (c4 == ('i') || c4 == ('I') || c4 == ('í') || c4 == ('Í')) {
                        c4 = 'I';
                    } else if (c4 == ('o') || c4 == ('O') || c4 == ('ó') || c4 == ('Ó')) {
                        c4 = 'O';
                    } else if (c4 == ('u') || c4 == ('U') || c4 == ('ú') || c4 == ('Ú')) {
                        c4 = 'U';
                    } else if (c4 == ('ñ') || c4 == ('Ñ') || c4==('/') || c4==('-') || c4==('.')) {
                        c4 = 'X';
                    }
                    for (int i=1; i <etSegundoNombre.length() && boolc16 == false; i++){
                        for (int j=0; j < consonantes.length; j++ ){
                            c16 = etSegundoNombre.getText().charAt(i);
                            if(c16 == consonantes[j]){
                                boolc16 = true;
                            }
                        }
                    }
                    if (c16 == ('a') || c16 == ('A') || c16 == ('á') || c16 == ('Á')) {
                        c16 = 'X';
                    } else if (c16 == ('e') || c16 == ('E') || c16 == ('é') || c16 == ('É')) {
                        c16 = 'X';
                    } else if (c16 == ('i') || c16 == ('I') || c16 == ('í') || c16 == ('Í')) {
                        c16 = 'X';
                    } else if (c16 == ('o') || c16 == ('O') || c16 == ('ó') || c16 == ('Ó')) {
                        c16 = 'X';
                    } else if (c16 == ('u') || c16 == ('U') || c16 == ('ú') || c16 == ('Ú')) {
                        c16 = 'X';
                    } else if (c16 == ('ñ') || c16 == ('Ñ') || c16==('/') || c16==('-') || c16==('.')) {
                        c16 = 'X';
                    }
                }

            } else {
                c4 = etPrimerNombre.getText().charAt(0);
                if (c4 == ('a') || c4 == ('A') || c4 == ('á') || c4 == ('Á')) {
                    c4 = 'A';
                } else if (c4 == ('e') || c4 == ('E') || c4 == ('é') || c4 == ('É')) {
                    c4 = 'E';
                } else if (c4 == ('i') || c4 == ('I') || c4 == ('í') || c4 == ('Í')) {
                    c4 = 'I';
                } else if (c4 == ('o') || c4 == ('O') || c4 == ('ó') || c4 == ('Ó')) {
                    c4 = 'O';
                } else if (c4 == ('u') || c4 == ('U') || c4 == ('ú') || c4 == ('Ú')) {
                    c4 = 'U';
                } else if (c4 == ('ñ') || c4 == ('Ñ') || c4==('/') || c4==('-') || c4==('.')) {
                    c4 = 'X';
                }
            }

            for (int i=1; i <etPrimerNombre.length() && boolc16 == false; i++){
                for (int j=0; j < consonantes.length; j++ ){
                    c16 = etPrimerNombre.getText().charAt(i);
                    if(c16 == consonantes[j]){
                        boolc16 = true;
                    }
                }
            }
            if (c16 == ('a') || c16 == ('A') || c16 == ('á') || c16 == ('Á')) {
                c16 = 'X';
            } else if (c16 == ('e') || c16 == ('E') || c16 == ('é') || c16 == ('É')) {
                c16 = 'X';
            } else if (c16 == ('i') || c16 == ('I') || c16 == ('í') || c16 == ('Í')) {
                c16 = 'X';
            } else if (c16 == ('o') || c16 == ('O') || c16 == ('ó') || c16 == ('Ó')) {
                c16 = 'X';
            } else if (c16 == ('u') || c16 == ('U') || c16 == ('ú') || c16 == ('Ú')) {
                c16 = 'X';
            } else if (c16 == ('ñ') || c16 == ('Ñ') || c16==('/') || c16==('-') || c16==('.') || c16==(' ')) {
                c16 = 'X';
            }
        }

        if (tvFechaNacPaso2.getText().toString().length()>0) {
            String annoTemporal = tvFechaNacPaso2.getText().toString().substring(0,4);
            Calendar fechaSistema = Calendar.getInstance();
            anno = fechaSistema.get(Calendar.YEAR);
            int noAnnoTemporal = Integer.parseInt(annoTemporal);

            annoCortoTemporal = tvFechaNacPaso2.getText().toString().substring(2,4);
            mesTemporal = tvFechaNacPaso2.getText().toString().substring(5,7);
            diaTemporal = tvFechaNacPaso2.getText().toString().substring(8,10);

        }

        armandoCurp += c1;
        armandoCurp += c2;
        armandoCurp += c3;
        armandoCurp += c4;
        armandoCurp.toUpperCase();
        pocoConvenientes = new String[] {"BACA","BAKA","BUEI","BUEY","CACA","CACO","CAGA","CAGO","CAKA","CAKO","COGE","COGI","COJA","COJE","COJI","COJO","COLA","CULO","FALO","FETO","GETA","GUEI","GUEY","JETA","JOTO","KACA","KACO","KAGA","KAGO","KAKA","KAKO","KOGE","KOGI","KOJA","KOJE","KOJI","KOJO","KOLA","KULO","LILO","LOCA","LOCO","LOKA","LOKO","MAME","MAMO","MEAR","MEAS","MEON","MIAR","MION","MOCO","MOKO","MULA","MULO","NACA","NACO","PEDA","PEDO","PENE","PIPI","PITO","POPO","PUTA","PUTO","QULO","RATA","ROBA","ROBE","ROBO","RUIN","SENO","TETA","VACA","VAGA","VAGO","VAKA","VUEI","VUEY","WUEI","WUEY"};
        for (int i = 0; i<pocoConvenientes.length; i++){
            if(armandoCurp.equals(pocoConvenientes[i])){
                c2 = 'X';
                armandoCurp = "" ;
                armandoCurp += c1;
                armandoCurp += c2;
                armandoCurp += c3;
                armandoCurp += c4;
            }
        }
        armandoCurp += annoCortoTemporal + mesTemporal + diaTemporal;
        armandoCurp += cg;
        armandoCurp += siglasEntidad;
        armandoCurp += c14;
        armandoCurp += c15;
        armandoCurp += c16;

        tvCurp.setText(armandoCurp.toUpperCase());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FOTO_INE:
                bitmapIne = (Bitmap)data.getExtras().get("data");
                tvPonerFoto.setImageBitmap(bitmapIne);
                break;
            case FOTO_PERFIL:
                bitmapFotoPerfil = (Bitmap)data.getExtras().get("data");
                ponerAvatar.setImageBitmap(bitmapFotoPerfil);
                break;
        }

    }

    public Bitmap rotateBitmap(Bitmap original, float degrees) {
        int width = original.getWidth();
        int height = original.getHeight();

        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);

        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, width, height, matrix, true);
        Canvas canvas = new Canvas(rotatedBitmap);
        canvas.drawBitmap(original, 5.0f, 0.0f, null);

        return rotatedBitmap;
    }

}
