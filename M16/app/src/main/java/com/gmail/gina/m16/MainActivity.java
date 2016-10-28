package com.gmail.gina.m16;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btndefault;
    private Button btnsinglechoice;
    private Button multiplechoices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();

    }

    private void initView(){
        btndefault = (Button)findViewById(R.id.btn_def);
        btnsinglechoice= (Button)findViewById(R.id.btnsingle);
        multiplechoices= (Button)findViewById(R.id.btnmulti);
    }

    private void initHandler() {
        btndefault.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Salut");
                        dialog.setMessage("Welcome");
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    }
                }
        );


        btnsinglechoice.setOnClickListener(
                new View.OnClickListener() {
                    int selectIndex =-1;
                   // final String[] products = getResources().getStringArray(R.array.products);
                     String[] products = getResources().getStringArray(R.array.products);
                   @Override public void onClick(View v) {
                       AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                       dialog.setTitle("choice one");
                       dialog.setSingleChoiceItems(products, selectIndex, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog ,int which) {
                                      //  Toast tosast= Toast.makeText(MainActivity.this,"U choice :"+products[selectIndex],Toast.LENGTH_LONG);
                                      //  tosast.show();
                                       selectIndex  =which;     // ??? by value ?????
                                   }
                               }
                       );
                       dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {   // which -> OK
                               Toast tosast = Toast.makeText(MainActivity.this, "Ur choice :" + products[selectIndex], Toast.LENGTH_LONG);
                               tosast.show();
                           }
                       });
                        dialog.show();
                   }
                }
        );


        multiplechoices.setOnClickListener(new View.OnClickListener() {

                    //?CharSequence[]
               final String[] days ={"MonDay","TUesday","wednesday","thursday","friday","saturday","sunday"};

               boolean[] isSelected =  new boolean[days.length];
             // for(int i=0 ;i<days.length;i++)

             @Override
             public void onClick(View v) {

               // final CharSequence[] days ={"MonDay","TUesday","wednesday","thursday","friday","saturday","sunday"};
                //boolean[] isSelected =  new boolean[days.length];
                AlertDialog.Builder dialog  = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("choice days ");
                dialog.setMultiChoiceItems(days,isSelected , new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                   isSelected[which] =  isChecked;
                         Log.i("gina", days[which] );
                                   // Toast toast = Toast.makeText(MainActivity.this, "yes:"+(isChecked? "selected":"deselected") ,Toast.LENGTH_LONG);
                                   // toast.show();
                    }
                });
               // dialog.setNegativeButton();
                //dialog.setNeutralButton();
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg ="your choice :";
                         for(int i =0;i<isSelected.length;i++)
                        {
                             if(isSelected[i]==true){msg+= days[i] +",";
                             Log.i("gina", days[i] );
                             }
                        }
                        Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG);
                        toast.show();
                         Log.i("gina", msg );
                    }
                }) ;
                dialog.show();
            }
        });

    }
}
