// PeopleController.aidl
package com.telyo.aidl.server;

// Declare any non-default types here with import statements
import com.telyo.aidl.server.People;
interface PeopleController {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     /**
      * 获取进程id
      */
     int getPid();

      /**
      * 获取List<People>
      */
     List<People> getPeople();
      /**
      * 增加People
      */
     void addPeople(inout People p);

}
