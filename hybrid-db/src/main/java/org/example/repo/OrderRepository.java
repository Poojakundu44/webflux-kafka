package org.example.repo;

import org.aspectj.weaver.ast.Or;
import org.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order ,Long> {
}
