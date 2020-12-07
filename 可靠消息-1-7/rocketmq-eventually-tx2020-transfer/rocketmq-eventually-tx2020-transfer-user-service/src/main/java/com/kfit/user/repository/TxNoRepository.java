package com.kfit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kfit.user.bean.TxNo;

public interface TxNoRepository extends JpaRepository<TxNo, String> {

}
