package org.kevoreetp.javase.demos;

import org.daum.library.javase.jtouchDB.TouchDBService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoreetp.common.model.Follower;
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
public class FakeLightCouchList extends AbstractComponentType implements Runnable{

    private boolean first = true;
    Thread t;
    private boolean alive=false;

    @Start
    public void start()
    {
        alive = true;
        t = new Thread(this);
        t.start();
    }


    @Stop
    public void stop()
    {
        alive = false;

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

            TouchDBService service = getPortByName("service", TouchDBService.class);

            String docname =getDictionary().get("name_document").toString();
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
