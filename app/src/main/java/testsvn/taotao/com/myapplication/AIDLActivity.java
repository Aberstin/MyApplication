package testsvn.taotao.com.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import testsvn.taotao.com.remote_service.IMyAidlInterface;
import testsvn.taotao.com.remote_service.Person;

public class AIDLActivity extends Activity implements View.OnClickListener{

    private IMyAidlInterface mRemoteService;

    private ServiceConnection mRemoteConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Toast.makeText(AIDLActivity.this, "BIND", Toast.LENGTH_SHORT).show();
            mRemoteService = IMyAidlInterface.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Toast.makeText(AIDLActivity.this, "UN_BIND", Toast.LENGTH_SHORT).show();
            mRemoteService = null;
        }

    };

    private Button btn_bind;
    private Button btn_add;
    private Button btn_list;
    private TextView tv_list;
    private TextView remote_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btn_bind = (Button) findViewById(R.id.bind_service);
        btn_add = (Button) findViewById(R.id.add_person);
        btn_list = (Button) findViewById(R.id.list_person);
        tv_list = (TextView) findViewById(R.id.list_data);
        remote_service = (TextView) findViewById(R.id.remote_service);
        btn_bind.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        remote_service.setOnClickListener(this);

        bindService(new Intent("testsvn.taotao.com.remote_service.RemoteService"),
                mRemoteConnection, Context.BIND_AUTO_CREATE);
    }


    boolean mIsRemoteBound = false;
    public void onBindService(boolean mIsRemoteBound){
        if (mIsRemoteBound) {
            btn_bind.setText("bind_RM_service");
            unbindService(mRemoteConnection);
        }else{
            btn_bind.setText("unbind_RM_service");
            bindService(new Intent("testsvn.taotao.com.remote_service.RemoteService"),
                    mRemoteConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private int index;

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.remote_service:
               finish();
               break;
           case R.id.bind_service:
               onBindService(mIsRemoteBound = !mIsRemoteBound);
               break;
           case R.id.list_person:
               List<Person> list = null;

               try {
                   list = mRemoteService.getAllPerson();
               } catch (RemoteException e) {
                   e.printStackTrace();
               }

               if (list != null){
                   StringBuilder text = new StringBuilder();

                   for(Person person : list){
                       text.append("PERSON name :");
                       text.append(person.getName());
                       text.append("  age :");
                       text.append(person.getAge());
                       text.append("  tel :");
                       text.append(person.getTelNumber());
                       text.append("\n");
                   }

                   tv_list.setText(text);
               }else {
                   Toast.makeText(AIDLActivity.this, "get data error",
                           Toast.LENGTH_SHORT).show();
               }
               break;
           case R.id.add_person:
               Person person = new Person();
               index = index + 1;
               person.setName("Person" + index);
               person.setAge(20);
               person.setTelNumber("123");
               try {
                   mRemoteService.savePersonInfo(person);
               } catch (RemoteException e) {
                   e.printStackTrace();
               }
               break;
       }
    }
}
