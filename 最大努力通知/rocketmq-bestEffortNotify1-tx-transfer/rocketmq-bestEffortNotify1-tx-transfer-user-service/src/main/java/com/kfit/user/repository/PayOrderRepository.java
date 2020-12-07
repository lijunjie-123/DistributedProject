package com.kfit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kfit.user.bean.PayOrder;

public interface PayOrderRepository extends JpaRepository<PayOrder, Long>{

}
