/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.Serializable;
import java.util.Objects;

/**
 *The parent class of all entity classes that contains an ID.
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
    
    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Entity o) {
        return this.id.compareTo(o.getId());
    }
    
    /**
     * The overrides of equals to compare 2 objects with their ids.
     * @param o the object to be compared.
     * @return true for equal, false for different.
     */
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
