package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    //SELECT res.user_id FROM (SELECT orders.user_id, SUM(orders.total_price) AS sum FROM orders GROUP BY orders.user_id) AS res HAVING MAX(sum)
    @Query(value = "SELECT orders.user_id, SUM(orders.total_price) FROM orders GROUP BY orders.user_id ORDER BY SUM(orders.total_price) DESC LIMIT 1", nativeQuery = true)
    long getUserWithHighestCostOfAllOrders();
}
