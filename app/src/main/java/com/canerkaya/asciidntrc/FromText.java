package com.canerkaya.asciidntrc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class FromText extends AppCompatActivity {
    TextView firstText;
    TextView octText;
    TextView hexText;
    TextView asciiText;
    EditText editText;
    String binResult;
    TextView first;
    Boolean textOrBinary=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_text);
        firstText =findViewById(R.id.firstText);
        octText=findViewById(R.id.octText);
        hexText=findViewById(R.id.hexText);
        asciiText=findViewById(R.id.asciiText);
        editText=findViewById(R.id.editText);
        first=findViewById(R.id.first);


    }
    public void convert(View view){
       if (textOrBinary) {// Metin oku çevir.
           try {
               String edit = editText.getText().toString();
               byte[] bytes = edit.getBytes();
               BigInteger bigInteger = new BigInteger(bytes);
               binResult = bigInteger.toString(2);
               firstText.setText("0" + binResult);
               octText.setText(bigInteger.toString(8));
               hexText.setText(bigInteger.toString(16));
               asciiText.setText(bigInteger.toString(10));
           } catch (Exception e) {
               AlertDialog.Builder alert = new AlertDialog.Builder(FromText.this);
               alert.setTitle("Lütfen geçerli bir metin giriniz...");
               alert.setNeutralButton("Geri dönmek için dokunun.", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               alert.show();

           }
       }
       else{  // Binary oku çevir.
           if (editText.getText().toString().length()==8) {
               try {
                   int charCode = Integer.parseInt(editText.getText().toString(), 2);
                   String text = new Character((char) charCode).toString();
                   firstText.setText(text);
                   byte[] bytes = text.getBytes();
                   BigInteger bigInteger = new BigInteger(bytes);
                   octText.setText(bigInteger.toString(8));
                   hexText.setText(bigInteger.toString(16));
                   asciiText.setText(bigInteger.toString(10));

               } catch (Exception e) {
                   AlertDialog.Builder alert = new AlertDialog.Builder(FromText.this);
                   alert.setTitle("Lütfen geçerli bir sayı giriniz...");
                   alert.setNeutralButton("Geri dönmek için dokunun.", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   alert.show();
               }
           }else {
               Toast.makeText(FromText.this,"8 bitlik değer girmelisiniz.",Toast.LENGTH_LONG).show();
           }
       }
    }
    public void turn(View view){
        firstText.setText("");
        octText.setText("");
        hexText.setText("");
        asciiText.setText("");
        editText.setText("");
        if (textOrBinary){ // Binary okuma modu
            textOrBinary=false;
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            first.setText("METİN");
            firstText.setHint("Metin Karşılığı");
            editText.setHint("Binary Değer Giriniz : ");
        }else { // Metin okuma modu
            textOrBinary=true;
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            first.setText("BINARY");
            firstText.setHint("Metinin Binary Karşılığı");
            editText.setHint("Metin giriniz");
        }
    }
}
