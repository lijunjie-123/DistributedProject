package com.kfit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kfit.user.bean.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	/**通过uid进行获取的账号信息*/
	Account findByUid(long uid);
}
