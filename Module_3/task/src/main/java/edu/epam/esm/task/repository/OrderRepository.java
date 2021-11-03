package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Order;
import edu.epam.esm.task.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT orders.user_id FROM orders HAVING SUM(orders.total_price) = (SELECT SUM(orders.total_price) FROM orders GROUP BY orders.user_id)", nativeQuery = true)
    long getUserWithHighestCostOfAllOrders();
}
