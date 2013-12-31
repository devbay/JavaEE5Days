package com.andrewkravets.model;

import javax.persistence.*;

/**
 * Created by Andrew Kravets.
 * Date: 12/31/13.
 * Url: andrewkravets.com
 */
@Entity
@Access(AccessType.FIELD)
public class Pills {
    @Id @GeneratedValue
    Long id;

    String title;

    public Pills(String title) {
        this.title = title;
    }

    public Pills() {
    }
}
