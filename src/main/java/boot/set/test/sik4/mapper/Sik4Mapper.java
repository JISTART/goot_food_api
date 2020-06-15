package boot.set.test.sik4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import boot.set.test.sik4.domain.TestVO;

@Mapper
public interface Sik4Mapper {
	public List<TestVO> test();

	public List<TestVO> search(TestVO testVO);

	public int insertStore(TestVO testVO);

	public int insertStar(TestVO testVO);

//	public void insertTag(List<String> list);
	public void insertTag(String tag);
}
