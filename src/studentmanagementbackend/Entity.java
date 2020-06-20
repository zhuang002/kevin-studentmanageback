/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author zhuan
 */
public abstract class Entity implements Serializable,Comparable<Entity> {
    private String id=null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(Entity o) {
        return this.id.compareTo(o.getId());
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Entity)
            return this.id.equals(((Entity)o).getId());
        return false;
    }

    
    
    abstract public void save();
    abstract public void update();
    abstract public void delete();
    
    
}
