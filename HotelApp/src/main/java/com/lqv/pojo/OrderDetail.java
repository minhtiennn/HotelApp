/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lqv.pojo;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class OrderDetail {
    private int id;
    private Date timeStart;
    private Date timeEnd;
    private int room_id;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the timeStart
     */
    public Date getTimeStart() {
        return timeStart;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @return the timeEnd
     */
    public Date getTimeEnd() {
        return timeEnd;
    }

    /**
     * @param timeEnd the timeEnd to set
     */
    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * @return the room_id
     */
    public int getRoom_id() {
        return room_id;
    }

    /**
     * @param room_id the room_id to set
     */
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
