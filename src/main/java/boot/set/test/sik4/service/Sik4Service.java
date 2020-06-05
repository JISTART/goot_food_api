package boot.set.test.sik4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.set.test.sik4.domain.TestVO;
import boot.set.test.sik4.mapper.Sik4Mapper;

@Service
public class Sik4Service {
	@Autowired
	Sik4Mapper sik4Mapper;
	
	public List<TestVO> test() {
		List<TestVO> resultList = new ArrayList<>();
		resultList = sik4Mapper.test();
		
		return resultList;
	}
}
