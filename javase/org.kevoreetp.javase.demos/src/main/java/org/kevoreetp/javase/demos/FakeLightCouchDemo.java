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
        @DictionaryAttribute(name = "name_document", defaultValue = "followers", optional = false)
})
@Requires({
        @RequiredPort(name = "service", type = PortType.SERVICE, className = TouchDBService.class, optional = false, needCheckDependency = true)
})
@ComponentType
public class FakeLightCouchDemo extends AbstractComponentType implements Runnable{

    private boolean first = true;
    Thread t;
    private boolean alive=false;

    @Start
    public void start()
    {
        alive = true;
        t = new Thread(this);
        t.start();

        String docname =getDictionary().get("name_document").toString();
        TouchDBService service = getPortByName("service", TouchDBService.class);
        service.addChangeListener(docname);
    }


    @Stop
    public void stop()
    {
        alive = false;
        String docname =getDictionary().get("name_document").toString();
        TouchDBService service = getPortByName("service", TouchDBService.class);
        service.removeChangeListener(docname);
    }

    @Update
    public void update() {


    }

    @Override
    public void run()
    {
        int i=0;
        while(alive)
        {

            String docname =getDictionary().get("name_document").toString();
            TouchDBService service = getPortByName("service", TouchDBService.class);


            Follower pompier1 = new Follower();
            pompier1.setMatricule("JeD "+i);
            double lat =    48.115683;
            double lon =         -1.664286;
            pompier1.lat=(int)(lat* 1E6);
            pompier1.lon =  (int)(lon* 1E6);
            pompier1.accuracy = 3;
            pompier1.altitude= 10;
            pompier1.safety_distance = 5;

            Response  m =    service.getDbClient(docname).save(pompier1);

            System.out.println("Saving "+pompier1.getMatricule()+" "+m.getId());
            i++;

            System.out.println("List ->");
            // get list
            for(  Follower f :  service.getDbClient(docname).view("_all_docs").includeDocs(true).query(Follower.class))
            {
                System.out.println(f.getMatricule());

            }
            try
            {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
