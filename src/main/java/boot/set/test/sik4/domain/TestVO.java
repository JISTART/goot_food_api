package boot.set.test.sik4.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVO {

//	private String usrId;
	private String tagSeq;
	private String tagName;
	private Integer storeSeq;
	private String storeName;
	private String storeTag;
	private String storeCate;
	private String storeStar;
	private String storeAddr;
	private List<String> convertTag;
	private String regId;
	private String regDt;
	private String review;
	private String storeImg;
	private String storeSiteUrl;
	// 크롤링 할 사이트 url,imgSrc
	private String siteWebUrl;
	private String cwalImg;

}
