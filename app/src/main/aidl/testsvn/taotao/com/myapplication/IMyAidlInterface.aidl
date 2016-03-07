// IMyAidlInterface.aidl
package testsvn.taotao.com.myapplication;

import testsvn.taotao.com.myapplication.Person;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    void savePersonInfo(in Person person);
    List<Person> getAllPerson();
}
