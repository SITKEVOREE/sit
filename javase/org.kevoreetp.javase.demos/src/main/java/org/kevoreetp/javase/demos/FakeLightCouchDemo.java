package org.kevoreetp.javase.demos;

import org.daum.library.javase.jtouchDB.TouchDBService;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.*;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;

import org.kevoree.framework.MessagePort;
import org.kevoreetp.common.model.Follower;
import org.lightcouch.Changes;
import org.lightcouch.ChangesResult;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 29/01/13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
@Library(name = "JavaSE")
@DictionaryType({
        @DictionaryAttribute(name = "touchdbport", defaultValue = "8888", optional = false),
        @DictionaryAttribute(name = "name_document", defaultValue = "followers", optional = false)
})
@Requires({
        @RequiredPort(name = "service", type = PortType.SERVICE, className = TouchDBService.class, optional = false, needCheckDependency = true)
})
@ComponentType
public class FakeLightCouchDemo extends AbstractComponentType {

    private boolean first = true;

    @Start
    public void start()
    {
        Follower pompier1 = new Follower();
        pompier1.setMatricule("Noel Plouzeau");
        double lat =    48.115683;
        double lon =         -1.664286;
        pompier1.lat=(int)(lat* 1E6);
        pompier1.lon =  (int)(lon* 1E6);
        pompier1.accuracy = 3;
        pompier1.altitude= 10;
        pompier1.safety_distance = 5;

        TouchDBService service = getPortByName("service", TouchDBService.class);


        String docname =getDictionary().get("name_document").toString();

        Response  m =    service.getDbClient(docname).save(pompier1);



        System.out.println(m.getId());


    }


    @Stop
    public void stop() {


    }

    @Update
    public void update() {


    }

}
