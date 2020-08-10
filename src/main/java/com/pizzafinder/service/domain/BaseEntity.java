package com.pizzafinder.service.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@MappedSuperclass
@XmlTransient
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof BaseEntity)) return false;

        if (super.equals(obj)) return true;

        if (getId() == null) return false;

        BaseEntity other = (BaseEntity) obj;

        if (other.getId() == null) return false;

        // Using Hibernate.getClass() to get the real entity class and not the proxy class
        if (!Hibernate.getClass(getClass()).equals(Hibernate.getClass(other.getClass())))
            return false;

        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
