// PeopleController.aidl
package com.telyo.aidl.server;

// Declare any non-default types here with import statements
import com.telyo.aidl.server.People;
interface PeopleController {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     int getPid();

     List<People> getPeople();

     void addPeople(inout People p);

}
