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
class Contact {
    String homephone;
    String cellphone;
    String email;

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }
}
