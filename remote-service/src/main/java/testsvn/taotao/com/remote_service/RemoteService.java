package testsvn.taotao.com.remote_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: testsvn.taotao.com.remote_service.RemoteService.java
 * @author: Aberstin
 * @date: 2016-03-06 14:46
 */
public class RemoteService extends Service {


    private LinkedList<Person> personList = new LinkedList<Person>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub(){

        @Override
        public void savePersonInfo(Person person) throws RemoteException {
            if (person != null){
                personList.add(person);
            }
        }

        @Override
        public List<Person> getAllPerson() throws RemoteException {
            return personList;
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(RemoteService.this,"UN_BINDED_SERVICE",Toast.LENGTH_SHORT).show();
        return true;
    }
}
