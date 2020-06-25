package boot.set.test.sik4.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.buf.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
			String cwalImg = testVO.getCwalImg().replace(" ", "+");
			System.out.println(cwalImg);

			String headers = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36";

			// WebDriver 경로 설정
	        System.setProperty("webdriver.chrome.driver", "C:/Users/4DETPH/Downloads/chromedriver/chromedriver.exe");

	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        options.addArguments(headers);

	        WebDriver driver = new ChromeDriver(options);
	        driver.get("https://duckduckgo.com/?q="+cwalImg+"&t=ht&iax=images&ia=images");

	        List<WebElement> img = driver.findElements(By.className("tile--img__img"));
	        System.out.println("1111 : " + img.get(0).getAttribute("src"));

	        testVO.setStoreImg(img.get(0).getAttribute("src"));

			sik4Service.insertStore(testVO);

			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("",HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<String>("",HttpStatus.OK);
	}

//	public static void main(String[] args) {
//
//		String url = "https://baekh-93.tistory.com/11";
//		Document doc = null;
//
//		try {
//			doc = Jsoup.connect(url).get();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		Elements element = doc.select("div.tt_article_useless_p_margin");
//
//		//Iterator을 사용하여 하나씩 값 가져오기
//        //덩어리안에서 필요한부분만 선택하여 가져올 수 있다.
//		Iterator<Element> ie1 = element.select("p").iterator();
//
//		while(ie1.hasNext()) {
//			System.out.println("크롤링 : " + ie1.next().text());
//		}
//
//		System.out.println("============================================================");
//
//	}

//	public static void main(String[] args) {
//
//		String siteUrl = "https://www.google.com/search?q=%EB%A1%AF%EB%8D%B0%EB%A6%AC%EC%95%84+%EC%97%AD%EC%82%BC%EC%A0%90&source=lnms";
//		Document doc = null;
//		String folder = "";
//		try {
//			doc = Jsoup.connect(siteUrl).get();
//			folder = doc.title();
//
//			Element element = doc.select("div.cont_essential").get(0);
//			Elements img = doc.select("div.thumb");
//			System.out.println("img : " + img);
//			int page = 0;
//
//			for(Element e : img) {
//				String url = e.getElementsByAttribute("src").attr("src");
//				String url = img.first().attr("style").replace("background-image:url('","").replace("')", "");
//				System.out.println("url:"+url);
//
//				URL imgUrl = new URL(url);
//				BufferedImage jpg = ImageIO.read(imgUrl);
//				File file = new File("C:\\Users\\4depth\\Desktop\\burger.jpg");
//				ImageIO.write(jpg, "jpg", file);
//				page+=1;
//			}
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
