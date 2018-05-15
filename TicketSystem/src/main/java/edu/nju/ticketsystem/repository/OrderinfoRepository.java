package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.OrderinfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderinfoRepository extends JpaRepository<OrderinfoEntity,Integer> {
    @Query("select w from OrderinfoEntity w where w.orderstate=:state and w.email=:email")
    Page<OrderinfoEntity> getOrderList(@Param("state") String state, @Param("email") String email, Pageable pageable);

    @Query("select count(w) from OrderinfoEntity w where w.orderstate=:state and w.email=:email ")
    int getTotalNumOfOrder(@Param("state") String state,@Param("email")String email);

    @Query("select w from OrderinfoEntity w where w.orderstate=:state ")
    List<OrderinfoEntity> getDealOrderList(@Param("state") String state);

    @Modifying
    @Transactional
    @Query("update OrderinfoEntity m set m.orderstate=:state,m.seatinfo=:seatinfo where m.id=:id")
    int updateOrder(@Param("state")String state,@Param("seatinfo")String seatinfo,@Param("id")int id);

    @Modifying
    @Transactional
    @Query("update OrderinfoEntity m set m.orderstate=:state where m.name=:name and m.seatinfo=:seatinfo")
    int updateOrderAfterCheck(@Param("state")String state,@Param("seatinfo")String seatinfo,@Param("name")String name);

    @Query("select w from OrderinfoEntity w where w.id=:id ")
    OrderinfoEntity getOrderInfo(@Param("id") int id);

    @Query("select w from OrderinfoEntity w where w.orderstate=:state and w.name=:name")
    List<OrderinfoEntity> getOrderList(@Param("state") String state,@Param("name")String name);

    @Query("select w from OrderinfoEntity w where w.email=?1 and w.date like ?2%")
    List<OrderinfoEntity> getUserOrderList(String email,String date);

    @Query("select count(w) from OrderinfoEntity w where w.email=?1 and w.date like ?2% and w.orderstate=?3")
    int getOrderNumber(String email,String date,String orderState);

    @Query("select w from OrderinfoEntity w where w.email=?1 and w.date like ?2% and w.orderstate=?3")
    List<OrderinfoEntity> getUserOrderList(String email,String date,String orderState);

    @Query("select w from OrderinfoEntity w where w.name=?1 and w.date like ?2% and w.orderstate=?3")
    List<OrderinfoEntity> getPlanOrderList(String name,String date,String orderState);

    @Query("select w from OrderinfoEntity w where w.name=?1 and w.date like ?2%")
    List<OrderinfoEntity> getPlanOrderList(String name,String date);
}

