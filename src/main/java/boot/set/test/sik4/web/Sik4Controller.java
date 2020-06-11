package boot.set.test.sik4.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.buf.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import boot.set.test.sik4.domain.TestVO;
import boot.set.test.sik4.service.Sik4Service;

@CrossOrigin
@RestController
public class Sik4Controller {
	@Autowired
	Sik4Service sik4Service;

	@GetMapping("/test")
	public @ResponseBody ResponseEntity<?> test() {
		Map<String,Object> resultMap = new HashMap<>();
		List<TestVO> resultList = sik4Service.test();
		resultMap.put("resultList", resultList);

		return new ResponseEntity<List>(resultList,HttpStatus.OK);

	}
	@RequestMapping("/")
	public String home() {
		return "hello";
	}

	@PostMapping("/search")
	public @ResponseBody ResponseEntity<?> search(@RequestBody List<String> strArr) {

		System.out.println(strArr);

		List<TestVO> resultList = sik4Service.search(strArr);
		for(int i =0; i<resultList.size(); i++) {
			resultList.get(i).setStoreTag(resultList.get(i).getStoreTag().replace("|", "#"));
		}
		System.out.println(resultList.size());

		return new ResponseEntity<List>(resultList,HttpStatus.OK);
	}

	@PostMapping("/insert")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody TestVO testVO) throws Exception {

		try {
			testVO.setStoreTag(StringUtils.join(testVO.getConvertTag(), '|'));
			sik4Service.insertStore(testVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("",HttpStatus.OK);
	}

	public static void main(String[] args) {

		String url = "https://toma0912.tistory.com/74";
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		}catch (Exception e) {
			e.printStackTrace();
		}

		Elements element = doc.select("div.home_news");

		String title = element.select("h3").text().substring(0, 4);

		System.out.println("============================================================");
		System.out.println(title);
		System.out.println("============================================================");

		for(Element el : element.select("li")) {
			System.out.println(el.text());
		}

		System.out.println("============================================================");

	}

}
