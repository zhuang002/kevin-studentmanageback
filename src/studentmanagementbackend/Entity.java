/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

/**
 *
 * @author zhuan
 */
public abstract class Entity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    abstract public void save();
    abstract public void update();
    abstract public void delete();
    abstract public Entity get(String id);
    
}
