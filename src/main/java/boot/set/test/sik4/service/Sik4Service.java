package boot.set.test.sik4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public List<TestVO> search(List<String> strArr) {
		List<TestVO> resultList = new ArrayList<>();
		resultList = sik4Mapper.search(strArr);
		return resultList;
	}

	
	public void insertStore(TestVO testVO) throws Exception {

		int cnt = sik4Mapper.insertStore(testVO);
		List<String> convertTag = testVO.getConvertTag();
		if(cnt < 0) {
			throw new Exception("insertStore 실패");
		} else {
			if(convertTag.size()>0) {
				for(int i=0;i<convertTag.size();i++) {
					sik4Mapper.insertTag(convertTag.get(i));
				}
			}
			cnt = sik4Mapper.insertStar(testVO);
			if(cnt < 0) {
				throw new Exception("insertStar 실패");
			}
		}

	}
}
