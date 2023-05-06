package org.utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Shedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
//    @ManyToMany()
//    private List<Date> dates;

    public String checkMasterWorkload(String masterName, Date startDate, Date endDate) {
        return null;
    }

    public String checkWorkShedule(Date date) {
        return null;
    }
}
