/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lqv.hotelapp;

import com.lqv.pojo.Order;
import com.lqv.pojo.OrderDetail;
import com.lqv.pojo.OrderOwner;
import com.lqv.pojo.Room;
import com.lqv.service.JdbcUtils;
import com.lqv.service.OrderDetailService;
import com.lqv.service.OrderOwnerService;
import com.lqv.service.OrderService;
import com.lqv.service.RoomService;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InfoCusController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCMND;
    @FXML
    private TextField txtDT;

    @FXML
    private TextField txtRoom;
    @FXML
    private TextField txtTien;

    @FXML
    private DatePicker dtStart;
    @FXML
    private DatePicker dtEnd;

    private Room room = new Room();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tải thông tin phòng mới được chọn - lấy id room -> name room
        //       

        System.out.println("Da thiet lap");

    }

    public int getDayStart() {
        LocalDate date = dtStart.getValue();
//        System.out.println("day start " + date.getDayOfYear());
        return date.getDayOfYear();
    }

    public void getDayEnd() {
        LocalDate date = dtEnd.getValue();
        BigDecimal day = new BigDecimal(date.getDayOfYear() - this.getDayStart());
        BigDecimal price = day.multiply(room.getPrice());
        String s = String.valueOf(price);
        this.txtTien.setText(s);
    }

    public void setRoom(String name) {
        this.txtRoom.setText(name);
    }

    public void getRoom(Room r) {
        room.setId(r.getId());
        room.setImage(r.getImage());
        room.setName(r.getName());
        room.setPrice(r.getPrice());
        room.setQuantity(r.getQuantity());
        room.setCategoryId(r.getCategoryId());
    }

    public Order addOrder() {
        Order order = new Order();
        order.setPay_status(true);

        String priceStr = this.txtTien.getText();
        BigDecimal price = new BigDecimal(priceStr);
        order.setTotal_price(price);

        order.setEmployee_id(3);

        return order;
    }

    public OrderDetail addOrderDetail() {
        OrderDetail detail = new OrderDetail();
        
        detail.setId(this.addOrder().getId());

//        LocalDate dayEnd = this.dtEnd.getValue();
//        detail.setTimeEnd(dayEnd.toString());
//
//        LocalDate dayStrat = this.dtEnd.getValue();
//        detail.setTimeStart(dayStrat.toString());

        detail.setRoom_id(room.getId());
        
        return detail;
    }

    public void addOrderOwner() {
        OrderOwner owner = new OrderOwner();

        owner.setId(this.addOrder().getId());
        owner.setName(this.txtName.getText());
        owner.setPhone(this.txtDT.getText());
        owner.setIS_Number(this.txtCMND.getText());

        Connection conn;
        try {           
            conn = JdbcUtils.getConn();
            
            OrderService order = new OrderService(conn);
            OrderDetailService detail = new OrderDetailService(conn);
            OrderOwnerService ownerS = new OrderOwnerService(conn);
            
            if (order.addOrder(this.addOrder()) == true) {
//                if (detail.addOrderDetail(this.addOrderDetail()) == true && ownerS.addOrderOwner(owner) == true) {
                    Utils.getAlertBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
//                }
            } else {
                Utils.getAlertBox("FAILED", Alert.AlertType.WARNING).show();
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(InfoCusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
